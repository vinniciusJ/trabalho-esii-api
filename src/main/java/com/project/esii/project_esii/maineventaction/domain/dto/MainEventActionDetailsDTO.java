package com.project.esii.project_esii.maineventaction.domain.dto;

import com.project.esii.project_esii.eventmanager.domain.dto.EventManagerDetailsDTO;
import com.project.esii.project_esii.mainevent.domain.dto.MainEventDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public record MainEventActionDetailsDTO(

        @NotNull
        Long id,

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
        EventManagerDetailsDTO eventManagerDetailsDTO,


        @NotNull
        Integer quantityVacancies,

        @NotNull
        MainEventDTO mainEventDTO
) {
}
