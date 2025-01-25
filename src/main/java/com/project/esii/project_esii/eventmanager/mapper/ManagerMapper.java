package com.project.esii.project_esii.eventmanager.mapper;

import com.project.esii.project_esii.eventmanager.domain.dto.EventManagerDTO;
import com.project.esii.project_esii.eventmanager.domain.entity.EventManager;

public class ManagerMapper {
    public static EventManagerDTO convertFromEntityToDTO(EventManager manager){
        return new EventManagerDTO(
                manager.getId(),
                manager.getCpfNumber(),
                manager.getName(),
                manager.getPhone(),
                manager.getEmail(),
                manager.getIsEmailVerified(),
                manager.getPersonRole()
        );
    }
}
