package com.project.esii.project_esii.maineventtype.mapper;

import com.project.esii.project_esii.maineventtype.domain.dto.MainEventTypeDTO;
import com.project.esii.project_esii.maineventtype.domain.entity.MainEventType;

public class EventTypeMapper {
    public static MainEventTypeDTO convertFromEntityToDTO(MainEventType eventType) {
        return new MainEventTypeDTO(eventType.getId(), eventType.getName());
    }
}
