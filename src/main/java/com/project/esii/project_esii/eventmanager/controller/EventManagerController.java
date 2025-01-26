package com.project.esii.project_esii.eventmanager.controller;

import com.project.esii.project_esii.emailsender.service.EmailSenderService;
import com.project.esii.project_esii.eventmanager.domain.dto.EventManagerDTO;
import com.project.esii.project_esii.eventmanager.domain.dto.EventManagerFormDTO;
import com.project.esii.project_esii.eventmanager.domain.entity.EventManager;
import com.project.esii.project_esii.eventmanager.mapper.ManagerMapper;
import com.project.esii.project_esii.eventmanager.service.EventManagerService;
import com.project.esii.project_esii.eventparticipant.service.EventParticipantService;
import com.project.esii.project_esii.exceptions.domain.ExistingRegistrationEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event-manager")
@RequiredArgsConstructor
public class EventManagerController {
    private final EventManagerService eventManagerService;
    private final EmailSenderService emailSenderService;

    @GetMapping
    public ResponseEntity<Page<EventManagerDTO>> findAll(Pageable pageable) {
        Page<EventManagerDTO> managers = eventManagerService.findAll(pageable);

        return ResponseEntity.ok(managers);
    }

    @PostMapping
    public ResponseEntity<EventManagerDTO> create(@RequestBody EventManagerFormDTO eventManagerFormDTO) {
        EventManager eventManager = eventManagerService.save(eventManagerFormDTO);

        emailSenderService.sendRegistrationVerificationEmail("/event-manager/verify-email/" + eventManager.getId(), eventManager.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(ManagerMapper.convertEntityToDTO(eventManager));
    }

    @PostMapping("/admin")
    public ResponseEntity<EventManagerDTO> createAdmin(@RequestBody EventManagerFormDTO eventManagerFormDTO) {
        EventManager eventManager = eventManagerService.saveAdmin(eventManagerFormDTO);

        emailSenderService.sendRegistrationVerificationEmail("/event-manager/verify-email/" + eventManager.getId(), eventManager.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(ManagerMapper.convertEntityToDTO(eventManager));
    }

    @PostMapping("/verify-email/{id}")
    public ResponseEntity<String> verifyEmail(@PathVariable Long id) {
        EventManager eventParticipant = eventManagerService.findById(id);
        EventManagerDTO eventManagerDetailsDTO = eventManagerService.setEmailToVerified(eventParticipant);

        return ResponseEntity.ok("Muito obrigado por confirmar seu cadastro, " + eventManagerDetailsDTO.name() + "!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventManagerDTO> getEventManagerById(@PathVariable Long id) {
        EventManager eventManager = eventManagerService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(ManagerMapper.convertEntityToDTO(eventManager));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        EventManager eventManager = eventManagerService.findById(id);
        eventManagerService.delete(eventManager);

        return ResponseEntity.noContent().build();
    }


}
