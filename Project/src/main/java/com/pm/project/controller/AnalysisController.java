package com.pm.project.controller;

import com.pm.project.service.AnalysisService;
import com.pm.project.service.ProblemService;
import com.pm.project.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
public class AnalysisController {

    private final UserService userService;

    private final ProblemService problemService;

    private final AnalysisService analysisService;

    public AnalysisController(UserService userService, ProblemService problemService, AnalysisService analysisService) {
        this.userService = userService;
        this.problemService = problemService;
        this.analysisService = analysisService;
    }

    @GetMapping(value = "/users-count", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Long> getNumberOfUsers() {
        return userService.streamUserCount();
    }

    @GetMapping("/number-of-users")
    public long numberOfUsers() {
        return userService.getNumberOfUsers();
    }

    @GetMapping(value = "/problems-count", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Long> getProblemsCount() {
        return problemService.streamProblemCount();
    }

    @GetMapping("/number-of-problems")
    public long getNumberOfProblems() {
        return problemService.getProblemCount();
    }

    @GetMapping(value = "/problem-status-sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<List<Long>> getProblemStatus() {
        return analysisService.problemStatus();
    }

    @GetMapping("/problem-status")
    public List<Long> getProblemStatusOnLoad() {
        return analysisService.getProblemStatus();
    }

    @GetMapping(value = "/department-sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<List<Long>> getDepartment() {
        return analysisService.problemDepartment();
    }

    @GetMapping("/departments")
    public List<Long> getDepartmentsOnLoad() {
        return analysisService.getDepartment();
    }
}
