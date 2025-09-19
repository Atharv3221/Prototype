package com.pm.project.dto;

import com.pm.project.entity.Coordinate;
import com.pm.project.entity.Department;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class ProblemDTO {
    private UUID userId;

    private String description;

    private MultipartFile imageFile;

    private Coordinate coordinate;

    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getFileName() {
        if (imageFile != null) {
            return imageFile.getOriginalFilename();
        }
        return null;
    }

}
