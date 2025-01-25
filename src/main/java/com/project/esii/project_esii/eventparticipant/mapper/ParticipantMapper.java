package com.project.esii.project_esii.eventparticipant.mapper;

import com.project.esii.project_esii.eventparticipant.domain.dto.EventParticipantDTO;
import com.project.esii.project_esii.eventparticipant.domain.entity.EventParticipant;

public class ParticipantMapper {
    public static EventParticipantDTO convertFromEntityToDTO(EventParticipant participant) {
        return new EventParticipantDTO(
                participant.getId(),
                participant.getCpfNumber(),
                participant.getName(),
                participant.getPhone(),
                participant.getEmail(),
                participant.getIsEmailVerified(),
                participant.getPersonRole()
        );
    }
}
