package com.project.esii.project_esii.maineventaction.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record MainEventActionFormDTO(

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
        Integer quantityVacancies,

        @NotNull
        Long mainEventId
) {

}
