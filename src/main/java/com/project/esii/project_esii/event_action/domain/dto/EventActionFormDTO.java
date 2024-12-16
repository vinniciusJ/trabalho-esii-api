package com.project.esii.project_esii.event_action.domain.dto;

import java.time.LocalDateTime;

public record EventActionFormDTO(
    String title,
    LocalDateTime startDate,
    LocalDateTime endDate,
    Double registrationFee,
    Integer availablePositions,
    Long responsibleId,
    Long eventId
) {}
