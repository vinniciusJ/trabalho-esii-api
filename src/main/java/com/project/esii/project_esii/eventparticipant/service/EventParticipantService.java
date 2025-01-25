package com.project.esii.project_esii.eventparticipant.service;

import com.project.esii.project_esii.enums.PersonRole;
import com.project.esii.project_esii.eventparticipant.domain.dto.EventParticipantDTO;
import com.project.esii.project_esii.eventparticipant.domain.dto.EventParticipantFormDTO;
import com.project.esii.project_esii.eventparticipant.domain.entity.EventParticipant;
import com.project.esii.project_esii.eventparticipant.mapper.EventParticipantMapper;
import com.project.esii.project_esii.eventparticipant.repository.EventParticipantRepository;
import com.project.esii.project_esii.exceptions.domain.EntityNotFoundExcpetion;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class EventParticipantService {

    private final EventParticipantRepository eventParticipantRepository;
    private final EventParticipantMapper eventParticipantMapper;

    public Page<EventParticipantDTO> findAll(Pageable pageable) {
        return eventParticipantRepository.findAll(pageable).map(eventParticipantMapper::convertEntityToDTO);
    }

    public EventParticipant save(EventParticipantFormDTO eventParticipantFormDTO) {
        EventParticipant eventParticipant = convertEventParticipantFormDTOToEventParticipant(eventParticipantFormDTO);

        eventParticipant.setPersonRole(PersonRole.ROLE_EVENT_PARTICIPANT);

        String encodedPassword = new BCryptPasswordEncoder().encode(eventParticipant.getPassword());
        eventParticipant.setPassword(encodedPassword);

        return eventParticipantRepository.save(eventParticipant);
    }

    public EventParticipant convertEventParticipantFormDTOToEventParticipant(EventParticipantFormDTO eventParticipantFormDTO) {
        return eventParticipantMapper.convertFormDTOToEntity(eventParticipantFormDTO);
    }

    public EventParticipantDTO convertEventParticipantToEventParticipantDetailsDTO(EventParticipant eventParticipant) {
        return eventParticipantMapper.convertEntityToDTO(eventParticipant);
    }

    public EventParticipant findById(Long id) {
        return eventParticipantRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExcpetion("EventParticipant", "id", id.toString())
        );
    }

    public EventParticipant findByCpfNumber(String cpfNumber) {
        return eventParticipantRepository.findByCpfCpfNumber(cpfNumber).orElseThrow(
                () -> new EntityNotFoundExcpetion("EventParticipant", "cpfNumber", cpfNumber)
        );
    }

    public EventParticipantDTO setEmailToVerified(EventParticipant eventParticipant) {
        eventParticipant.setIsEmailVerified(true);
        eventParticipantRepository.save(eventParticipant);
        return convertEventParticipantToEventParticipantDetailsDTO(eventParticipant);
    }

    public void delete(EventParticipant eventParticipant) {
        eventParticipantRepository.delete(eventParticipant);
    }

    public EventParticipant findByEmail(String email) {
        return eventParticipantRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return eventParticipantRepository.existsByEmail(email);
    }
}
