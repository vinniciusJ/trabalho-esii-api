package com.project.esii.project_esii.mainevent.mapper;

import com.project.esii.project_esii.eventmanager.domain.entity.EventManager;
import com.project.esii.project_esii.mainevent.domain.dto.MainEventDetailsDTO;
import com.project.esii.project_esii.mainevent.domain.dto.MainEventFormDTO;
import com.project.esii.project_esii.mainevent.domain.entity.MainEvent;
import com.project.esii.project_esii.maineventaction.mapper.MainEventActionMapper;
import com.project.esii.project_esii.maineventtype.domain.entity.MainEventType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MainEventActionMapper.class})
public interface MainEventMapper {

    @Mapping(source = "mainEventFormDTO.title", target = "title")
    @Mapping(source = "mainEventFormDTO.startDateTime", target = "startDateTime")
    @Mapping(source = "mainEventFormDTO.endDateTime", target = "endDateTime")
    @Mapping(source = "mainEventFormDTO.registrationPrice", target = "registrationPrice")
    @Mapping(source = "mainEventFormDTO.address", target = "address")
    @Mapping(source = "eventManager", target = "eventManager")
    @Mapping(source = "mainEventType", target = "mainEventType")
    @Mapping(target = "id", ignore = true)
    MainEvent convertMainEventFormDTOToMainEvent(MainEventFormDTO mainEventFormDTO, EventManager eventManager, MainEventType mainEventType);

    @Mapping(source = "mainEvent.mainEventType", target = "mainEventTypeDetailsDTO")
    @Mapping(source = "mainEvent.eventManager", target = "eventManagerDetailsDTO")
    @Mapping(source = "mainEvent.eventManager.cpf.cpfNumber", target = "eventManagerDetailsDTO.cpfNumber")
    @Mapping(source = "mainEvent.mainEventActionList", target = "mainEventActionDetailsDTOList")
    MainEventDetailsDTO convertMainEventToMainEventDetailsDTO(MainEvent mainEvent);
}
