package com.pm.project.controller;

import com.pm.project.entity.Problem;
import com.pm.project.service.ProblemService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ProblemController {

    private final ProblemService problemService;

    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @PostMapping("/upload-problem")
    public int uploadProblem(@RequestBody Problem problem) {
        return problemService.saveProblem(problem);
    }

    @GetMapping(value = "/problems/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Problem> streamProblems() {
        return problemService.streamProblem();
    }
}
