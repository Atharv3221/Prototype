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
    private ProblemState problemState;

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

    public void setProblemState(ProblemState problemState) {
        this.problemState = problemState;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
        problemState = ProblemState.OPEN;
    }

    public ProblemState getProblemState() {
        return problemState;
    }

}
