package com.pm.project.service;

import com.pm.project.entity.Department;
import com.pm.project.entity.ProblemStatus;
import com.pm.project.repository.ProblemRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalysisService {

    private final ProblemRepository problemRepository;

    private final Sinks.Many<List<Long>> problemStatusSink = Sinks.many().multicast().onBackpressureBuffer();

    private final Sinks.Many<List<Long>> problemDepartmentSink = Sinks.many().multicast().onBackpressureBuffer();

    public AnalysisService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public void updateProblemStatus() {
        problemStatusSink.tryEmitNext(getProblemStatus());
    }

    public Flux<List<Long>> problemStatus() {
        return problemStatusSink.asFlux();
    }

    public List<Long> getProblemStatus() {
        List<Long> statusList = new ArrayList<>();
        long open = problemRepository.countByProblemStatus(ProblemStatus.OPEN);
        long inProgress = problemRepository.countByProblemStatus(ProblemStatus.IN_PROGRESS);
        long resolved = problemRepository.countByProblemStatus(ProblemStatus.RESOLVED);
        long close = problemRepository.countByProblemStatus(ProblemStatus.CLOSE);
        statusList.add(open);
        statusList.add(inProgress);
        statusList.add(resolved);
        statusList.add(close);
        return statusList;
    }

    public void updateProblemDepartments() {
        problemDepartmentSink.tryEmitNext(getDepartment());
    }

    public Flux<List<Long>> problemDepartment() {
        return problemDepartmentSink.asFlux();
    }

    public List<Long> getDepartment() {
        List<Long> departments = new ArrayList<>();
        long roadAndTransport = problemRepository.countByDepartment(Department.ROAD_AND_TRANSPORT);
        long sanitation = problemRepository.countByDepartment(Department.SANITATION);
        long infrastructure = problemRepository.countByDepartment(Department.INFRASTRUCTURE);
        long publicOrder = problemRepository.countByDepartment(Department.PUBLIC_ORDER);
        long electricityAndPowers = problemRepository.countByDepartment(Department.ELECTRICITY_AND_POWERS);
        long govBodyIssues = problemRepository.countByDepartment(Department.GOV_BODY_ISSUES);
        departments.add(roadAndTransport);
        departments.add(sanitation);
        departments.add(infrastructure);
        departments.add(publicOrder);
        departments.add(electricityAndPowers);
        departments.add(govBodyIssues);
        return departments;
    }
}
