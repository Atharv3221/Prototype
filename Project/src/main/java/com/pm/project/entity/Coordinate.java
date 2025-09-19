package com.pm.project.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public record Coordinate(double latitude, double longitude) {
}
