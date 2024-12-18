package com.project.esii.project_esii.mainevent.domain.dto;

import com.project.esii.project_esii.eventmanager.domain.dto.EventManagerDetailsDTO;
import com.project.esii.project_esii.maineventaction.domain.dto.MainEventActionDetailsDTO;
import com.project.esii.project_esii.maineventtype.domain.dto.MainEventTypeDetailsDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record MainEventDTO(

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
        MainEventTypeDetailsDTO mainEventTypeDetailsDTO
) {
}
