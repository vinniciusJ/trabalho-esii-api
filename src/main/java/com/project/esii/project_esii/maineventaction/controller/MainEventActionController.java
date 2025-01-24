package com.project.esii.project_esii.maineventaction.controller;

import com.project.esii.project_esii.eventmanager.domain.entity.EventManager;
import com.project.esii.project_esii.eventmanager.service.EventManagerService;
import com.project.esii.project_esii.eventparticipant.domain.entity.EventParticipant;
import com.project.esii.project_esii.eventparticipant.service.EventParticipantService;
import com.project.esii.project_esii.mainevent.domain.entity.MainEvent;
import com.project.esii.project_esii.mainevent.service.MainEventService;
import com.project.esii.project_esii.maineventaction.domain.dto.MainEventActionDetailsDTO;
import com.project.esii.project_esii.maineventaction.domain.dto.MainEventActionFormDTO;
import com.project.esii.project_esii.maineventaction.domain.entity.MainEventAction;
import com.project.esii.project_esii.maineventaction.service.MainEventActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/main-event/{eventId}/action")
@RequiredArgsConstructor
public class MainEventActionController {

    private final MainEventActionService mainEventActionService;
    private final EventManagerService eventManagerService;
    private final MainEventService mainEventService;
    private final EventParticipantService eventParticipantService;

    @PostMapping
    public ResponseEntity<MainEventActionDetailsDTO> create(@RequestBody MainEventActionFormDTO mainEventActionFormDTO) {
        MainEvent mainEvent = mainEventService.findById(mainEventActionFormDTO.mainEventId());
        EventManager eventManager = eventManagerService.findById(mainEventActionFormDTO.eventManagerId());

        MainEventAction mainEventAction = mainEventActionService.save(mainEventActionFormDTO, mainEvent, eventManager);

        return ResponseEntity.status(HttpStatus.CREATED).body(mainEventActionService.convertMainEventActionToMainEventActionDetailsDTO(mainEventAction));
    }

    @GetMapping
    public ResponseEntity<Page<MainEventActionDetailsDTO>> list(Pageable pageable) {
        Page<MainEventAction> mainEventActionPage = mainEventActionService.findAll(pageable);

        return ResponseEntity.status(HttpStatus.CREATED).body(mainEventActionService.convertToMainEventActionDetailsDTOPage(mainEventActionPage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MainEventActionDetailsDTO> findById(@PathVariable Long id) {
        MainEventAction mainEventAction = mainEventActionService.findById(id);

        return ResponseEntity.status(HttpStatus.CREATED).body(mainEventActionService.convertMainEventActionToMainEventActionDetailsDTO(mainEventAction));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        MainEventAction mainEventAction = mainEventActionService.findById(id);
        mainEventActionService.delete(mainEventAction);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/participant")
    public ResponseEntity<Void> subscribeParticipant(@PathVariable Long eventId, @PathVariable Long id, @RequestBody Long participantId) {
        MainEvent event = mainEventService.findById(eventId);
        EventParticipant eventParticipant = eventParticipantService.findById(id);

        mainEventActionService.subscribeParticipant(id, eventParticipant, event);

        return ResponseEntity.noContent().build();
    }
}
