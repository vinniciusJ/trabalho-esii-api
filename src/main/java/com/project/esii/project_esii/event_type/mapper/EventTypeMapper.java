package com.project.esii.project_esii.mappers;

import com.project.esii.project_esii.event_type.domain.dto.EventTypeDTO;
import com.project.esii.project_esii.event_type.domain.entity.EventType;

public class EventTypeMapper {
    public static EventType convertFormToEventType(String name){
        return EventType.builder().name(name).build();
    }

    public static EventType convertFormToEventType(Long id, String name){
        return EventType.builder().id(id).name(name).build();
    }


    public static EventTypeDTO convertToDTO(EventType eventType){
        return new EventTypeDTO(eventType.getId(), eventType.getName());
    }
}
