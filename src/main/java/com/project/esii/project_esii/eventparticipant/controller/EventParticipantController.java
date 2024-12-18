package com.project.esii.project_esii.eventparticipant.controller;

import com.project.esii.project_esii.emailsender.service.EmailSenderService;
import com.project.esii.project_esii.eventparticipant.domain.dto.EventParticipantDetailsDTO;
import com.project.esii.project_esii.eventparticipant.domain.dto.EventParticipantFormDTO;
import com.project.esii.project_esii.eventparticipant.domain.entity.EventParticipant;
import com.project.esii.project_esii.eventparticipant.service.EventParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event-participant")
@RequiredArgsConstructor
public class EventParticipantController {

    private final EventParticipantService eventParticipantService;
    private final EmailSenderService emailSenderService;

    @PostMapping
    public ResponseEntity<EventParticipantDetailsDTO> create(@RequestBody EventParticipantFormDTO eventParticipantFormDTO) {
        EventParticipant eventParticipant = eventParticipantService.save(eventParticipantFormDTO);

        emailSenderService.sendEventVerificationEmail("/event-participant/verify-email/" + eventParticipant.getId(), eventParticipant.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(eventParticipantService.convertEventParticipantToEventParticipantDetailsDTO(eventParticipant));
    }

    @PostMapping("/verify-email/{id}")
    public ResponseEntity<String> verifyEmail(@PathVariable Long id) {
        EventParticipant eventParticipant = eventParticipantService.findById(id);
        EventParticipantDetailsDTO eventParticipantDetailsDTO = eventParticipantService.setEmailToVerified(eventParticipant);

        return ResponseEntity.ok("Muito obrigado por confirmar seu cadastro, " + eventParticipantDetailsDTO.name() + "!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventParticipantDetailsDTO> getEventParticipantById(@PathVariable Long id) {
        EventParticipant eventParticipant = eventParticipantService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(eventParticipantService.convertEventParticipantToEventParticipantDetailsDTO(eventParticipant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        EventParticipant eventParticipant = eventParticipantService.findById(id);
        eventParticipantService.delete(eventParticipant);

        return ResponseEntity.noContent().build();
    }
}
