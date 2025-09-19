package com.pm.project.repository;

import com.pm.project.entity.Department;
import com.pm.project.entity.Problem;
import com.pm.project.entity.ProblemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Integer> {
    long countByProblemStatus(ProblemStatus status);
    long countByDepartment(Department department);
}
