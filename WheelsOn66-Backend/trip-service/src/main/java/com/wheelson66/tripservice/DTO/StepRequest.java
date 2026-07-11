package com.wheelson66.tripservice.DTO;

public record StepRequest(
        String locationName,
        double latitude,
        double longitude,
        Integer orderIndex
) {}