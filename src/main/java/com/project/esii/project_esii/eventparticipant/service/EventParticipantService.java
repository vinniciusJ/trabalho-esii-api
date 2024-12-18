package com.project.esii.project_esii.eventparticipant.service;

import com.project.esii.project_esii.authentication.domain.entity.BaseUser;
import com.project.esii.project_esii.eventparticipant.domain.dto.EventParticipantDetailsDTO;
import com.project.esii.project_esii.eventparticipant.domain.dto.EventParticipantFormDTO;
import com.project.esii.project_esii.eventparticipant.domain.entity.EventParticipant;
import com.project.esii.project_esii.eventparticipant.mapper.EventParticipantMapper;
import com.project.esii.project_esii.eventparticipant.repository.EventParticipantRepository;
import com.project.esii.project_esii.excpetions.type.EntityNotFoundExcpetion;
import com.project.esii.project_esii.security.SecurityConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class EventParticipantService {

    private final EventParticipantRepository eventParticipantRepository;
    private final EventParticipantMapper eventParticipantMapper;

    public EventParticipant save(EventParticipantFormDTO eventParticipantFormDTO) {
        EventParticipant eventParticipant = convertEventParticipantFormDTOToEventParticipant(eventParticipantFormDTO);

        String encodedPassword = new BCryptPasswordEncoder().encode(eventParticipant.getPassword());
        eventParticipant.setPassword(encodedPassword);

        return eventParticipantRepository.save(eventParticipant);
    }

    public EventParticipant convertEventParticipantFormDTOToEventParticipant(EventParticipantFormDTO eventParticipantFormDTO) {
        return eventParticipantMapper.convertEventParticipantFormDTOToEventParticipant(eventParticipantFormDTO);
    }

    public EventParticipantDetailsDTO convertEventParticipantToEventParticipantDetailsDTO(EventParticipant eventParticipant) {
        return eventParticipantMapper.convertEventParticipantToEventParticipantDetailsDTO(eventParticipant);
    }

    public EventParticipant findById(Long id) {
        return eventParticipantRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExcpetion("EventParticipant", "id", id.toString())
        );
    }

    public EventParticipantDetailsDTO setEmailToVerified(EventParticipant eventParticipant) {
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
}
