package com.project.esii.project_esii.event.mapper;

import com.project.esii.project_esii.event.domain.dto.EventDTO;
import com.project.esii.project_esii.event.domain.entity.Event;
import com.project.esii.project_esii.event_type.mapper.EventTypeMapper;
import com.project.esii.project_esii.user.mapper.UserMapper;

public class EventMapper {
    public static EventDTO convertEntityToDTO(Event event) {
        return new EventDTO(
            event.getId(),
            event.getTitle(),
                event.getStartDate(),
                event.getEndDate(),
                event.getRegistrationFee(),
                event.getAvailablePositions(),
                EventTypeMapper.convertToDTO(event.getEventType()),
                UserMapper.convertEntityToPlainUserDTO(event.getResponsible()),
                event.getParticipants().stream().map(UserMapper::convertEntityToPlainUserDTO).toList()
        );
    }
}
