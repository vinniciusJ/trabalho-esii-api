package com.project.esii.project_esii.maineventaction.mapper;

import com.project.esii.project_esii.eventmanager.domain.entity.EventManager;
import com.project.esii.project_esii.eventmanager.mapper.ManagerMapper;
import com.project.esii.project_esii.eventparticipant.domain.dto.EventParticipantDTO;
import com.project.esii.project_esii.eventparticipant.domain.entity.EventParticipant;
import com.project.esii.project_esii.eventparticipant.mapper.ParticipantMapper;
import com.project.esii.project_esii.mainevent.domain.dto.MainEventFormDTO;
import com.project.esii.project_esii.mainevent.domain.entity.MainEvent;
import com.project.esii.project_esii.maineventaction.domain.dto.MainEventActionDTO;
import com.project.esii.project_esii.maineventaction.domain.dto.MainEventActionFormDTO;
import com.project.esii.project_esii.maineventaction.domain.entity.MainEventAction;

import java.util.ArrayList;
import java.util.List;

public class EventActionMapper {
    public static MainEventActionDTO convertEntityToDTO(MainEventAction action) {
        Integer availableVacancies = action.getQuantityVacancies() - action.getEventParticipants().size();

        return new MainEventActionDTO(
                action.getId(),
                action.getTitle(),
                action.getStartDateTime(),
                action.getEndDateTime(),
                action.getRegistrationPrice(),
                action.getAddress(),
                ManagerMapper.convertFromEntityToDTO(action.getEventManager()),
                availableVacancies,
                action.getQuantityVacancies(),
                convertParticipantsToDTO(action.getEventParticipants())
        );
    }

    public static MainEventAction convertFormToEntity(MainEventActionFormDTO form, EventManager eventManager, MainEvent event) {
        MainEventAction action = new MainEventAction();

        action.setTitle(form.title());
        action.setStartDateTime(form.startDateTime());
        action.setEndDateTime(form.endDateTime());
        action.setRegistrationPrice(form.registrationPrice());
        action.setAddress(form.address());
        action.setQuantityVacancies(form.quantityVacancies());
        action.setEventManager(eventManager);
        action.setMainEvent(event);
        action.setEventParticipants(new ArrayList<>());

        return action;
    }

    private static List<EventParticipantDTO> convertParticipantsToDTO(List<EventParticipant> participants) {
        return participants.stream().map(ParticipantMapper::convertFromEntityToDTO).toList();
    }
}
