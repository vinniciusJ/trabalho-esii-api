package com.project.esii.project_esii.mainevent.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record MainEventFormDTO(


        @NotBlank
        String title,

        @NotNull
        LocalDateTime startDateTime,

        @NotNull
        LocalDateTime endDateTime,

        @NotNull

        Double registrationPrice,

        @NotBlank
        String address,

        @NotNull
        Long eventManagerId,

        @NotNull
        Long mainEventTypeId
) {
}
