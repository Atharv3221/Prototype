package com.pm.project.service;

import com.pm.project.entity.Problem;
import com.pm.project.entity.ProblemStatus;
import com.pm.project.repository.ProblemRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemService {

    private final ProblemRepository problemRepository;

    private final AnalysisService analysisService;

    private final Sinks.Many<Problem> sink = Sinks.many().multicast().onBackpressureBuffer();

    private final Sinks.Many<Long> problemCountSink = Sinks.many().multicast().onBackpressureBuffer();

    public ProblemService(ProblemRepository problemRepository, AnalysisService analysisService) {
        this.problemRepository = problemRepository;
        this.analysisService = analysisService;
    }

    public int saveProblem(Problem problem) {
        Problem savedProblem = problemRepository.save(problem);
        sink.tryEmitNext(savedProblem);
        long problemsCount = getProblemCount();
        problemCountSink.tryEmitNext(problemsCount);
        analysisService.updateProblemStatus();
        return problem.getId();
    }

    public Flux<Problem> streamProblem() {
        return sink.asFlux();
    }

    public Flux<Long> streamProblemCount() {
        return problemCountSink.asFlux();
    }

    public long getProblemCount() {
        return problemRepository.count();
    }

    public List<Problem> getAllproblems() {
        return problemRepository.findAll();
    }

    public String problemStatusUpdate(int id, ProblemStatus problemStatus) {
        Optional<Problem> optionalProblem = problemRepository.findById(id);
        if (optionalProblem.isEmpty()) {
            return "Problem not found";
        }
        Problem problem = optionalProblem.get();
        problem.setProblemStatus(problemStatus);
        problemRepository.save(problem);
        analysisService.updateProblemStatus();
        return "Problem status updated successfuly";
    }

    public String problemDescriptionUpdate(int id, String description) {
        Optional<Problem> optionalProblem = problemRepository.findById(id);
        if (optionalProblem.isEmpty()) {
            return "Problem not found";
        }
        Problem problem = optionalProblem.get();
        problem.setDescription(description);
        problemRepository.save(problem);
        analysisService.updateProblemStatus();
        return "Problem description updated successfuly";
    }
}
