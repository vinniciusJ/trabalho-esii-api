package com.project.esii.project_esii.eventsubscription.domain.dto;

import com.project.esii.project_esii.eventparticipant.domain.dto.EventParticipantDetailsDTO;
import com.project.esii.project_esii.mainevent.domain.dto.MainEventDTO;
import com.project.esii.project_esii.maineventaction.domain.dto.MainEventActionDetailsDTO;

import java.util.List;

public record EventSubscriptionDetailsDTO(
        Long id,

        MainEventDTO mainEventDTO,

        EventParticipantDetailsDTO eventParticipantDetailsDTO,

        List<MainEventActionDetailsDTO> mainEventActionDetailsDTOList
) {
}
