package com.project.esii.project_esii.eventmanager.controller;

import com.project.esii.project_esii.emailsender.service.EmailSenderService;
import com.project.esii.project_esii.eventmanager.domain.dto.EventManagerDetailsDTO;
import com.project.esii.project_esii.eventmanager.domain.dto.EventManagerFormDTO;
import com.project.esii.project_esii.eventmanager.domain.entity.EventManager;
import com.project.esii.project_esii.eventmanager.service.EventManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event-manager")
@RequiredArgsConstructor
public class EventManagerController {

    private final EventManagerService eventManagerService;
    private final EmailSenderService emailSenderService;

    @PostMapping
    public ResponseEntity<EventManagerDetailsDTO> create(@RequestBody EventManagerFormDTO eventManagerFormDTO) {
        EventManager eventManager = eventManagerService.save(eventManagerFormDTO);

        emailSenderService.sendEventVerificationEmail("/event-manager/verify-email/" + eventManager.getId(), eventManager.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(eventManagerService.convertEventManagerToEventManagerDetailsDTO(eventManager));
    }

    @PostMapping("/verify-email/{id}")
    public ResponseEntity<String> verifyEmail(@PathVariable Long id) {
        EventManager eventParticipant = eventManagerService.findById(id);
        EventManagerDetailsDTO eventManagerDetailsDTO = eventManagerService.setEmailToVerified(eventParticipant);

        return ResponseEntity.ok("Muito obrigado por confirmar seu cadastro, " + eventManagerDetailsDTO.name() + "!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventManagerDetailsDTO> getEventManagerById(@PathVariable Long id) {
        EventManager eventManager = eventManagerService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(eventManagerService.convertEventManagerToEventManagerDetailsDTO(eventManager));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        EventManager eventManager = eventManagerService.findById(id);
        eventManagerService.delete(eventManager);

        return ResponseEntity.noContent().build();
    }


}
