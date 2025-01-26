package com.project.esii.project_esii.eventparticipant.mapper;

import com.project.esii.project_esii.cpf.domain.entity.Cpf;
import com.project.esii.project_esii.enums.PersonRole;
import com.project.esii.project_esii.eventparticipant.domain.dto.EventParticipantDTO;
import com.project.esii.project_esii.eventparticipant.domain.dto.EventParticipantFormDTO;
import com.project.esii.project_esii.eventparticipant.domain.entity.EventParticipant;

public class ParticipantMapper {
    public static EventParticipantDTO convertEntityToDTO(EventParticipant participant) {
        return new EventParticipantDTO(
                participant.getId(),
                participant.getCpfNumber(),
                participant.getName(),
                participant.getPhone(),
                participant.getEmail(),
                participant.getIsEmailVerified()
        );
    }

    public static EventParticipant convertFormDTOToEntity(EventParticipantFormDTO form) {
        EventParticipant participant = new EventParticipant();

        participant.setCpf(new Cpf(form.cpfNumber()));
        participant.setName(form.name());
        participant.setPhone(form.phone());
        participant.setEmail(form.email());
        participant.setIsEmailVerified(false);
        participant.setPersonRole(PersonRole.ROLE_EVENT_PARTICIPANT);

        return participant;
    }
}
