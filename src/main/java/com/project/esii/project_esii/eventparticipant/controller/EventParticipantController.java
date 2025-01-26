package com.project.esii.project_esii.eventparticipant.controller;

import com.project.esii.project_esii.emailsender.service.EmailSenderService;
import com.project.esii.project_esii.eventmanager.domain.dto.EventManagerDTO;
import com.project.esii.project_esii.eventmanager.service.EventManagerService;
import com.project.esii.project_esii.eventparticipant.domain.dto.EventParticipantDTO;
import com.project.esii.project_esii.eventparticipant.domain.dto.EventParticipantFormDTO;
import com.project.esii.project_esii.eventparticipant.domain.entity.EventParticipant;
import com.project.esii.project_esii.eventparticipant.mapper.ParticipantMapper;
import com.project.esii.project_esii.eventparticipant.service.EventParticipantService;
import com.project.esii.project_esii.exceptions.domain.ExistingRegistrationEmailException;
import com.project.esii.project_esii.exceptions.domain.RegistrationEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event-participant")
@RequiredArgsConstructor
public class EventParticipantController {

    private final EventParticipantService eventParticipantService;
    private final EmailSenderService emailSenderService;
    private final EventManagerService eventManagerService;

    @GetMapping
    public ResponseEntity<Page<EventParticipantDTO>> findAll(Pageable pageable) {
        Page<EventParticipantDTO> managers = eventParticipantService.findAll(pageable);

        return ResponseEntity.ok(managers);
    }

    @PostMapping
    public ResponseEntity<EventParticipantDTO> create(@RequestBody EventParticipantFormDTO eventParticipantFormDTO) {
        if(eventManagerService.existsByEmail(eventParticipantFormDTO.email())) throw new ExistingRegistrationEmailException("Organizador de Evento", eventParticipantFormDTO.email());
        EventParticipant eventParticipant = eventParticipantService.save(eventParticipantFormDTO);

        if(!emailSenderService.sendRegistrationVerificationEmail("/event-participant/verify-email/" + eventParticipant.getId(), eventParticipant.getEmail())) {
            eventParticipantService.delete(eventParticipant);
            throw new RegistrationEmailException();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(ParticipantMapper.convertEntityToDTO(eventParticipant));
    }

    @PostMapping("/verify-email/{id}")
    public ResponseEntity<String> verifyEmail(@PathVariable Long id) {
        EventParticipant eventParticipant = eventParticipantService.findById(id);
        EventParticipantDTO eventParticipantDetailsDTO = eventParticipantService.setEmailToVerified(eventParticipant);

        return ResponseEntity.ok("Muito obrigado por confirmar seu cadastro, " + eventParticipantDetailsDTO.name() + "!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventParticipantDTO> getEventParticipantById(@PathVariable Long id) {
        EventParticipant eventParticipant = eventParticipantService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(ParticipantMapper.convertEntityToDTO(eventParticipant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        EventParticipant eventParticipant = eventParticipantService.findById(id);
        eventParticipantService.delete(eventParticipant);

        return ResponseEntity.noContent().build();
    }
}
