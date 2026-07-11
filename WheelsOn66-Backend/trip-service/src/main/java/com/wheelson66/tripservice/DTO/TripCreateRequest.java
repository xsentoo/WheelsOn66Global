package com.wheelson66.tripservice.DTO;

import com.wheelson66.tripservice.Entity.Enums.Visibility;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TripCreateRequest(
        @NotBlank(message = "Le titre du voyage est obligatoire")
        @Size(max = 100, message = "Le titre ne doit pas dépasser 100 caractères")
        String title,

        @NotNull(message = "La visibilité doit être définie")
        Visibility visibility
) {}