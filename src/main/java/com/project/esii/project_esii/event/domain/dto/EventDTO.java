package com.project.esii.project_esii.event.domain.dto;

import com.project.esii.project_esii.event_type.domain.dto.EventTypeDTO;
import com.project.esii.project_esii.user.domain.dto.PlainUserDTO;

import java.time.LocalDate;
import java.util.List;

public record EventDTO(
        Long id,
        String title,
        LocalDate startDate,
        LocalDate endDate,
        Double registrationFee,
        Integer availablePositions,
        EventTypeDTO eventType,
        PlainUserDTO responsible,
        List<PlainUserDTO> participants
){
}
