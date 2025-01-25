package com.project.esii.project_esii.mainevent.mapper;

import com.project.esii.project_esii.eventmanager.domain.entity.EventManager;
import com.project.esii.project_esii.eventmanager.mapper.ManagerMapper;
import com.project.esii.project_esii.eventparticipant.domain.dto.EventParticipantDTO;
import com.project.esii.project_esii.eventparticipant.domain.entity.EventParticipant;
import com.project.esii.project_esii.eventparticipant.mapper.ParticipantMapper;
import com.project.esii.project_esii.mainevent.domain.dto.MainEventDTO;
import com.project.esii.project_esii.mainevent.domain.dto.MainEventFormDTO;
import com.project.esii.project_esii.mainevent.domain.entity.MainEvent;
import com.project.esii.project_esii.maineventaction.domain.dto.MainEventActionDTO;
import com.project.esii.project_esii.maineventaction.domain.entity.MainEventAction;
import com.project.esii.project_esii.maineventaction.mapper.EventActionMapper;
import com.project.esii.project_esii.maineventtype.domain.entity.MainEventType;
import com.project.esii.project_esii.maineventtype.mapper.EventTypeMapper;

import java.util.ArrayList;
import java.util.List;

public class EventMapper {
    public static MainEventDTO convertEntityToDTO(MainEvent event) {
        return new MainEventDTO(
                event.getId(),
                event.getTitle(),
                event.getStartDateTime(),
                event.getEndDateTime(),
                event.getRegistrationPrice(),
                event.getAddress(),
                ManagerMapper.convertFromEntityToDTO(event.getEventManager()),
                EventTypeMapper.convertFromEntityToDTO(event.getMainEventType()),
                convertParticipantsToDTO(event.getEventParticipants()),
                convertEventActionsToDTO(event.getMainEventActionList())
        );
    }

    public static MainEvent convertFormToEntity(MainEvent mainEvent, MainEventFormDTO event, MainEventType type, EventManager manager) {
        MainEvent updatedMainEvent = new MainEvent();

        updatedMainEvent.setTitle(event.title());
        updatedMainEvent.setStartDateTime(event.startDateTime());
        updatedMainEvent.setEndDateTime(event.endDateTime());
        updatedMainEvent.setRegistrationPrice(event.registrationPrice());
        updatedMainEvent.setAddress(event.address());
        updatedMainEvent.setEventManager(manager);
        updatedMainEvent.setMainEventType(type);

        if(mainEvent != null) {
            updatedMainEvent.setMainEventActionList(mainEvent.getMainEventActionList());
            updatedMainEvent.setEventParticipants(mainEvent.getEventParticipants());
        } else {
            updatedMainEvent.setMainEventActionList(new ArrayList<>());
            updatedMainEvent.setEventParticipants(new ArrayList<>());
        }

        return updatedMainEvent;
    }

    private static List<EventParticipantDTO> convertParticipantsToDTO(List<EventParticipant> participants) {
        return participants.stream().map(ParticipantMapper::convertFromEntityToDTO).toList();
    }

    private static List<MainEventActionDTO> convertEventActionsToDTO(List<MainEventAction> eventActions) {
        return eventActions.stream().map(EventActionMapper::convertEntityToDTO).toList();
    }
}
