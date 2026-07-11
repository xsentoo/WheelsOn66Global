package com.wheelson66.tripservice.DTO;

import com.wheelson66.tripservice.Entity.Enums.StepType;
import java.time.Instant;

public record StepResponse(
        Long id,
        Integer orderIndex,
        StepType stepType,
        String locationName,
        Double latitude,
        Double longitude,
        Instant arrivalTimestamp
) {}