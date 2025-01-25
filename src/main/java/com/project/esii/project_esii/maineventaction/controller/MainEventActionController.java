package com.project.esii.project_esii.maineventaction.controller;

import com.project.esii.project_esii.eventmanager.domain.entity.EventManager;
import com.project.esii.project_esii.eventmanager.service.EventManagerService;
import com.project.esii.project_esii.eventparticipant.domain.entity.EventParticipant;
import com.project.esii.project_esii.eventparticipant.service.EventParticipantService;
import com.project.esii.project_esii.mainevent.domain.entity.MainEvent;
import com.project.esii.project_esii.mainevent.service.MainEventService;
import com.project.esii.project_esii.maineventaction.domain.dto.MainEventActionDTO;
import com.project.esii.project_esii.maineventaction.domain.dto.MainEventActionFiltersDTO;
import com.project.esii.project_esii.maineventaction.domain.dto.MainEventActionFormDTO;
import com.project.esii.project_esii.maineventaction.domain.entity.MainEventAction;
import com.project.esii.project_esii.maineventaction.mapper.EventActionMapper;
import com.project.esii.project_esii.maineventaction.service.MainEventActionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/main-event-action")
@RequiredArgsConstructor
@Transactional
public class MainEventActionController {

    private final MainEventActionService mainEventActionService;
    private final EventManagerService eventManagerService;
    private final MainEventService mainEventService;
    private final EventParticipantService eventParticipantService;

    @PostMapping
    public ResponseEntity<MainEventActionDTO> create(@RequestBody MainEventActionFormDTO mainEventActionFormDTO) {
        MainEvent mainEvent = mainEventService.findById(mainEventActionFormDTO.mainEventId());
        EventManager eventManager = eventManagerService.findById(mainEventActionFormDTO.eventManagerId());

        MainEventAction mainEventAction = mainEventActionService.save(mainEventActionFormDTO, mainEvent, eventManager);

        return ResponseEntity.status(HttpStatus.CREATED).body(EventActionMapper.convertEntityToDTO(mainEventAction));
    }

    @GetMapping
    public ResponseEntity<Page<MainEventActionDTO>> list(Pageable pageable, MainEventActionFiltersDTO filters) {
        Page<MainEventActionDTO> mainEventActions = mainEventActionService.findAll(filters, pageable);

        return ResponseEntity.ok(mainEventActions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MainEventActionDTO> findById(@PathVariable Long id) {
        MainEventAction mainEventAction = mainEventActionService.findById(id);

        return ResponseEntity.ok(EventActionMapper.convertEntityToDTO(mainEventAction));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        MainEventAction mainEventAction = mainEventActionService.findById(id);
        mainEventActionService.delete(mainEventAction);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/participant/{participantId}")
    public ResponseEntity<Void> subscribeParticipant(@PathVariable Long id, @PathVariable Long participantId){
        EventParticipant participant = eventParticipantService.findById(participantId);

        mainEventActionService.subscribeParticipant(id, participant);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/participant/{participantId}")
    public ResponseEntity<Void> unsubscribeParticipant(@PathVariable Long id, @PathVariable Long participantId){
        EventParticipant participant = eventParticipantService.findById(participantId);

        mainEventActionService.unsubscribeParticipant(id, participant);

        return ResponseEntity.noContent().build();
    }
}
