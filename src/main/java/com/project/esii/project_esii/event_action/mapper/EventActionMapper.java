package com.project.esii.project_esii.event_action.mapper;

import com.project.esii.project_esii.event.domain.entity.Event;
import com.project.esii.project_esii.event.mapper.EventMapper;
import com.project.esii.project_esii.event_action.domain.dto.EventActionDTO;
import com.project.esii.project_esii.event_action.domain.dto.EventActionFormDTO;
import com.project.esii.project_esii.event_action.domain.entity.EventAction;
import com.project.esii.project_esii.user.domain.entity.User;
import com.project.esii.project_esii.user.mapper.UserMapper;

public class EventActionMapper {
    public static EventAction convertFormToEntity(EventActionFormDTO eventAction, Event event, User responsible) {
        return EventAction
                .builder()
                .title(eventAction.title())
                .startDate(eventAction.startDate())
                .endDate(eventAction.endDate())
                .registrationFee(eventAction.registrationFee())
                .availablePositions(eventAction.availablePositions())
                .responsible(responsible)
                .event(event)
                .build();
    }


    public static EventActionDTO convertEntityToDTO(EventAction eventAction) {
        return new EventActionDTO(
                eventAction.getId(),
                eventAction.getTitle(),
                eventAction.getStartDate(),
                eventAction.getEndDate(),
                eventAction.getRegistrationFee(),
                eventAction.getAvailablePositions(),
                EventMapper.convertEntityToDTO(eventAction.getEvent()),
                UserMapper.convertEntityToPlainUserDTO(eventAction.getResponsible()),
                eventAction.getParticipants().stream().map(UserMapper::convertEntityToPlainUserDTO).toList()
        );
    }
}
