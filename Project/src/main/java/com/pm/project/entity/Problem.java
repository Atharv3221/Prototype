package com.pm.project.entity;


import jakarta.persistence.Embedded;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private UUID userId;

    private String description;

    private String imageURL;

    @Embedded
    private Coordinate coordinate;

    private LocalDate createdAt;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Enumerated(EnumType.STRING)
    private ProblemStatus problemStatus;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public LocalDate getUpload() {
        return createdAt;
    }

    public void setProblemStatus(ProblemStatus problemStatus) {
        this.problemStatus = problemStatus;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
        problemStatus = ProblemStatus.OPEN;
    }

    public ProblemStatus getProblemStatus() {
        return problemStatus;
    }

}
