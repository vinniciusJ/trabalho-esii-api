package com.project.esii.project_esii.eventmanager.mapper;

import com.project.esii.project_esii.cpf.domain.entity.Cpf;
import com.project.esii.project_esii.enums.PersonRole;
import com.project.esii.project_esii.eventmanager.domain.dto.EventManagerDTO;
import com.project.esii.project_esii.eventmanager.domain.dto.EventManagerFormDTO;
import com.project.esii.project_esii.eventmanager.domain.entity.EventManager;

public class ManagerMapper {
    public static EventManagerDTO convertEntityToDTO(EventManager manager){
        return new EventManagerDTO(
                manager.getId(),
                manager.getCpfNumber(),
                manager.getName(),
                manager.getPhone(),
                manager.getEmail(),
                manager.getIsEmailVerified()
        );
    }

    public static EventManager convertFormDTOToEntity(EventManagerFormDTO form){
        EventManager manager = new EventManager();

        manager.setName(form.name());
        manager.setCpf(new Cpf(form.cpfNumber()));
        manager.setPhone(form.phone());
        manager.setEmail(form.email());
        manager.setIsEmailVerified(form.isEmailVerified());
        manager.setPersonRole(PersonRole.ROLE_EVENT_MANAGER);

        return manager;
    }
}
