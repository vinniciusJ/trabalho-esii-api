package com.project.esii.project_esii.eventmanager.service;

import com.project.esii.project_esii.enums.PersonRole;
import com.project.esii.project_esii.eventmanager.domain.dto.EventManagerDetailsDTO;
import com.project.esii.project_esii.eventmanager.domain.dto.EventManagerFormDTO;
import com.project.esii.project_esii.eventmanager.domain.entity.EventManager;
import com.project.esii.project_esii.eventmanager.mapper.EventManagerMapper;
import com.project.esii.project_esii.eventmanager.repository.EventManagerRepository;
import com.project.esii.project_esii.excpetions.type.EntityNotFoundExcpetion;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventManagerService {

    private final EventManagerRepository eventManagerRepository;
    private final EventManagerMapper eventManagerMapper;

    public EventManager findById(Long id) {
        return eventManagerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExcpetion("EventManager", "id", id.toString())
        );
    }

    public EventManager save(EventManagerFormDTO eventManagerFormDTO) {
        EventManager eventManager = convertEventManagerFormDTOToEventManager(eventManagerFormDTO);

        eventManager.setPersonRole(PersonRole.ROLE_EVENT_MANAGER);

        String encodedPassword = new BCryptPasswordEncoder().encode(eventManager.getPassword());
        eventManager.setPassword(encodedPassword);

        return eventManagerRepository.save(eventManager);
    }

    private EventManager convertEventManagerFormDTOToEventManager(EventManagerFormDTO eventManagerFormDTO) {
        return eventManagerMapper.convertEventManagerFormDTOToEventManager(eventManagerFormDTO);
    }

    public EventManagerDetailsDTO convertEventManagerToEventManagerDetailsDTO(EventManager eventManager) {
        return eventManagerMapper.convertEventManagerToEventManagerDetailsDTO(eventManager);
    }

    public EventManagerDetailsDTO setEmailToVerified(EventManager eventManager) {
        eventManager.setIsEmailVerified(true);
        eventManagerRepository.save(eventManager);
        return convertEventManagerToEventManagerDetailsDTO(eventManager);
    }

    public void delete(EventManager eventManager) {
        eventManagerRepository.delete(eventManager);
    }

    public EventManager findByEmail(String email) {
        return eventManagerRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return eventManagerRepository.existsByEmail(email);
    }

    public EventManager findByCpfNumber(String cpfNumber) {
        EventManager eventManager = eventManagerRepository.findByCpfNumber(cpfNumber);
        if(eventManager == null) throw new EntityNotFoundExcpetion("EventManager", "cpfNumber", cpfNumber);
        return eventManager;
    }
}
