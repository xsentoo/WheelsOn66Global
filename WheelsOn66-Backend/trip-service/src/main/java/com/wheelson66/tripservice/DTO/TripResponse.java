package com.wheelson66.tripservice.DTO;

import com.wheelson66.tripservice.Entity.Enums.TripStatus;
import com.wheelson66.tripservice.Entity.Enums.Visibility;

import java.util.List;
import java.util.UUID;

public record TripResponse(
        UUID id,
        String tripHash,
        String title,
        Visibility visibility,
        TripStatus status,
        Integer totalDistanceKm,
        Integer totalDurationMinutes,
        List<StepResponse> steps
) {}