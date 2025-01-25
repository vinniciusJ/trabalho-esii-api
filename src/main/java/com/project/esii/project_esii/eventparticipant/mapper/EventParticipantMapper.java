package com.project.esii.project_esii.eventparticipant.mapper;

import com.project.esii.project_esii.eventparticipant.domain.dto.EventParticipantDTO;
import com.project.esii.project_esii.eventparticipant.domain.dto.EventParticipantFormDTO;
import com.project.esii.project_esii.eventparticipant.domain.entity.EventParticipant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventParticipantMapper {

    @Mapping(source = "eventParticipantFormDTO.cpfNumber", target = "cpf.cpfNumber")
    EventParticipant convertFormDTOToEntity(EventParticipantFormDTO eventParticipantFormDTO);

    @Mapping(source = "eventParticipant.cpf.cpfNumber", target = "cpfNumber")
    @Mapping(source = "eventParticipant.personRole", target = "personRole")
    EventParticipantDTO convertEntityToDTO(EventParticipant eventParticipant);
}
