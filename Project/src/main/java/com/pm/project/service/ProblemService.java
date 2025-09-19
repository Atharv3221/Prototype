package com.pm.project.service;

import com.pm.project.entity.Problem;
import com.pm.project.repository.ProblemRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Service
public class ProblemService {

    private final ProblemRepository problemRepository;

    private final Sinks.Many<Problem> sink = Sinks.many().multicast().onBackpressureBuffer();

    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public int saveProblem(Problem problem) {
        Problem savedProblem = problemRepository.save(problem);
        sink.tryEmitNext(savedProblem);
        return problem.getId();
    }

    public Flux<Problem> streamProblem() {
        return sink.asFlux();
    }
}
