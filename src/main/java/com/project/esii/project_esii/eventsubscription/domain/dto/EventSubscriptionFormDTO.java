package com.project.esii.project_esii.eventsubscription.domain.dto;

import jakarta.validation.constraints.NotNull;

public record EventSubscriptionFormDTO(

        @NotNull
        String eventParticipantCpf,

        @NotNull
        Long mainEventId
) { }
