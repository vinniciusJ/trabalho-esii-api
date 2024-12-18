package com.project.esii.project_esii.maineventaction.mapper;

import com.project.esii.project_esii.eventmanager.domain.entity.EventManager;
import com.project.esii.project_esii.mainevent.domain.entity.MainEvent;
import com.project.esii.project_esii.maineventaction.domain.dto.MainEventActionDetailsDTO;
import com.project.esii.project_esii.maineventaction.domain.dto.MainEventActionFormDTO;
import com.project.esii.project_esii.maineventaction.domain.entity.MainEventAction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MainEventActionMapper {

    @Mapping(source = "mainEventActionFormDTO.title", target = "title")
    @Mapping(source = "mainEventActionFormDTO.startDateTime", target = "startDateTime")
    @Mapping(source = "mainEventActionFormDTO.endDateTime", target = "endDateTime")
    @Mapping(source = "mainEventActionFormDTO.registrationPrice", target = "registrationPrice")
    @Mapping(source = "mainEventActionFormDTO.address", target = "address")
    @Mapping(source = "mainEvent", target = "mainEvent")
    @Mapping(source = "eventManager", target = "eventManager")
    @Mapping(target = "id", ignore = true)
    MainEventAction convertMainEventActionFormDTOToMainEventAction(MainEventActionFormDTO mainEventActionFormDTO, MainEvent mainEvent, EventManager eventManager);

    @Mapping(source = "mainEventAction.mainEvent", target = "mainEventDTO")
    @Mapping(source = "mainEventAction.eventManager", target = "eventManagerDetailsDTO")
    @Mapping(source = "mainEventAction.eventManager.cpf.cpfNumber", target = "eventManagerDetailsDTO.cpfNumber")
    @Mapping(source = "mainEventAction.mainEvent.mainEventType", target = "mainEventDTO.mainEventTypeDetailsDTO")
    @Mapping(source = "mainEventAction.mainEvent.eventManager", target = "mainEventDTO.eventManagerDetailsDTO")
    @Mapping(source = "mainEventAction.mainEvent.eventManager.cpf.cpfNumber", target = "mainEventDTO.eventManagerDetailsDTO.cpfNumber")
    MainEventActionDetailsDTO convertMainEventActionToMainEventActionDetailsDTO(MainEventAction mainEventAction);
}
