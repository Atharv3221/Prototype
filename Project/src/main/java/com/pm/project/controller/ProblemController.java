package com.pm.project.controller;

import com.pm.project.dto.ProblemDTO;
import com.pm.project.entity.Problem;
import com.pm.project.entity.ProblemStatus;
import com.pm.project.service.ProblemService;
import com.pm.project.service.StorageService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;

import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.List;

@RestController
public class ProblemController {

    private final ProblemService problemService;

    private final StorageService storageService;

    public ProblemController(ProblemService problemService, StorageService storageService) {
        this.problemService = problemService;
        this.storageService = storageService;
    }

    @PostMapping("/upload-problem")
    public int uploadProblem(@ModelAttribute ProblemDTO problemDTO) throws IOException {
        Problem problem = new Problem();
        problem.setUserId(problemDTO.getUserId());
        problem.setDescription(problemDTO.getDescription());
        problem.setImageURL(storageService.uploadFile(problemDTO.getImageFile(), problemDTO.getFileName()));
        problem.setCoordinate(problemDTO.getCoordinate());
        problem.setDepartment(problemDTO.getDepartment());
        return problemService.saveProblem(problem);
    }

    @GetMapping(value = "/problems/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Problem> streamProblems() {
        return problemService.streamProblem();
    }

    @GetMapping("/problems-all")
    public List<Problem> getAllProblems() {
        return problemService.getAllproblems();
    }

    @PutMapping("/problem-status-update/{id}")
    public String updateProblemStatus(@PathVariable int id, @RequestBody ProblemStatus problemStatus) {
        return problemService.problemStatusUpdate(id, problemStatus);
    }

    @PutMapping("/problem-description-update/{id}")
    public String updateProblemDescription(@PathVariable int id, @RequestBody String description) {
        return problemService.problemDescriptionUpdate(id, description);
    }
}
