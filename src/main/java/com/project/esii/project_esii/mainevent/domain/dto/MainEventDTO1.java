package com.project.esii.project_esii.mainevent.domain.dto;

import com.project.esii.project_esii.eventmanager.domain.dto.EventManagerDTO;
import com.project.esii.project_esii.eventparticipant.domain.dto.EventParticipantDTO;
import com.project.esii.project_esii.maineventtype.domain.dto.MainEventTypeDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record MainEventDTO1(

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
        EventManagerDTO eventManagerDetailsDTO,

        @NotNull
        MainEventTypeDTO mainEventTypeDetailsDTO,

        @NotNull List<EventParticipantDTO> participants
) {
}
