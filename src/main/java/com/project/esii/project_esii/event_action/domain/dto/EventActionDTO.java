package com.project.esii.project_esii.event_action.domain.dto;

import com.project.esii.project_esii.event.domain.dto.EventDTO;
import com.project.esii.project_esii.user.domain.dto.PlainUserDTO;
import com.project.esii.project_esii.user.domain.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public record EventActionDTO (
        Long id,
        String title,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Double registrationFee,
        Integer availablePositions,
        EventDTO event,
        PlainUserDTO responsible,
        List<PlainUserDTO> participants
) {
}
