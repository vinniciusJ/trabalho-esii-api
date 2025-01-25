package com.project.esii.project_esii.eventmanager.service;

import com.project.esii.project_esii.eventmanager.domain.dto.EventManagerDTO;
import com.project.esii.project_esii.eventmanager.domain.dto.EventManagerFormDTO;
import com.project.esii.project_esii.eventmanager.domain.entity.EventManager;
import com.project.esii.project_esii.eventmanager.mapper.EventManagerMapper;
import com.project.esii.project_esii.eventmanager.repository.EventManagerRepository;
import com.project.esii.project_esii.exceptions.domain.EntityNotFoundExcpetion;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EventManagerService {

    private final EventManagerRepository eventManagerRepository;
    private final EventManagerMapper eventManagerMapper;

    public Page<EventManagerDTO> findAll(Pageable pageable) {
        return eventManagerRepository.findAll(pageable).map(eventManagerMapper::convertEntityToDTO);
    }

    public EventManager findById(Long id) {
        return eventManagerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExcpetion("EventManager", "id", id.toString())
        );
    }

    public EventManager save(EventManagerFormDTO eventManagerFormDTO) {
        EventManager eventManager = convertEventManagerFormDTOToEventManager(eventManagerFormDTO);

        eventManager.setPersonRole(eventManagerFormDTO.personRole());

        String encodedPassword = new BCryptPasswordEncoder().encode(eventManager.getPassword());
        eventManager.setPassword(encodedPassword);

        return eventManagerRepository.save(eventManager);
    }

    private EventManager convertEventManagerFormDTOToEventManager(EventManagerFormDTO eventManagerFormDTO) {
        return eventManagerMapper.convertFormDTOToEntity(eventManagerFormDTO);
    }

    public EventManagerDTO convertEventManagerToEventManagerDetailsDTO(EventManager eventManager) {
        return eventManagerMapper.convertEntityToDTO(eventManager);
    }

    public EventManagerDTO setEmailToVerified(EventManager eventManager) {
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

    public EventManager getOrNull(Long id) {
        if(id == null) return null;
        return eventManagerRepository.findById(id).orElse(null);
    }
}
