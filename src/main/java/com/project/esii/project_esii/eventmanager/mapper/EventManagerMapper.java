package com.project.esii.project_esii.eventmanager.mapper;

import com.project.esii.project_esii.eventmanager.domain.dto.EventManagerDTO;
import com.project.esii.project_esii.eventmanager.domain.dto.EventManagerFormDTO;
import com.project.esii.project_esii.eventmanager.domain.entity.EventManager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventManagerMapper {

    @Mapping(source = "eventManagerFormDTO.cpfNumber", target = "cpf.cpfNumber")
    EventManager convertFormDTOToEntity(EventManagerFormDTO eventManagerFormDTO);

    @Mapping(source = "eventManager.cpf.cpfNumber", target = "cpfNumber")
    @Mapping(source = "eventManager.personRole", target = "personRole")
    EventManagerDTO convertEntityToDTO(EventManager eventManager);
}
