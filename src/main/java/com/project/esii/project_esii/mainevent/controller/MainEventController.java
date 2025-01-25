package com.project.esii.project_esii.mainevent.controller;

import com.project.esii.project_esii.eventmanager.domain.entity.EventManager;
import com.project.esii.project_esii.eventmanager.service.EventManagerService;
import com.project.esii.project_esii.eventparticipant.domain.entity.EventParticipant;
import com.project.esii.project_esii.eventparticipant.service.EventParticipantService;
import com.project.esii.project_esii.mainevent.domain.dto.MainEventDTO;
import com.project.esii.project_esii.mainevent.domain.dto.MainEventFiltersDTO;
import com.project.esii.project_esii.mainevent.domain.dto.MainEventFormDTO;
import com.project.esii.project_esii.mainevent.domain.entity.MainEvent;
import com.project.esii.project_esii.mainevent.mapper.EventMapper;
import com.project.esii.project_esii.mainevent.service.MainEventService;
import com.project.esii.project_esii.maineventtype.domain.entity.MainEventType;
import com.project.esii.project_esii.maineventtype.service.MainEventTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/main-event")
@RequiredArgsConstructor
public class MainEventController {

    private final MainEventService mainEventService;
    private final MainEventTypeService mainEventTypeService;
    private final EventManagerService eventManagerService;
    private final EventParticipantService eventParticipantService;

    @PostMapping
    public ResponseEntity<MainEventDTO> create(@RequestBody MainEventFormDTO mainEventFormDTO) {
        MainEventType mainEventType = mainEventTypeService.findById(mainEventFormDTO.mainEventTypeId());
        EventManager eventManager = eventManagerService.findByCpfNumber(mainEventFormDTO.eventManagerCpfNumber());

        MainEvent mainEvent = mainEventService.save(mainEventFormDTO, eventManager, mainEventType);

        return ResponseEntity.status(HttpStatus.CREATED).body(EventMapper.convertEntityToDTO(mainEvent));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MainEventDTO> update(@PathVariable Long id, @RequestBody MainEventFormDTO mainEventFormDTO) {
        MainEvent mainEvent = mainEventService.findById(id);
        MainEventType mainEventType = mainEventTypeService.findById(mainEventFormDTO.mainEventTypeId());
        EventManager eventManager = eventManagerService.findByCpfNumber(mainEventFormDTO.eventManagerCpfNumber());

        if(!eventManager.getCpfNumber().equals(mainEvent.getEventManager().getCpfNumber())) {
            throw new com.project.esii.project_esii.excpetions.type.NotAllowedToUpdateException("MainEvent", "cpfNumber", eventManager.getCpfNumber());
        }

        return ResponseEntity.status(HttpStatus.OK).body(mainEventService.update(mainEvent, eventManager, mainEventType, mainEventFormDTO));
    }

    @GetMapping
    public ResponseEntity<Page<MainEventDTO>> list(Pageable pageable, MainEventFiltersDTO filters) {
        Page<MainEventDTO> events = mainEventService.findAll(filters, pageable);

        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MainEventDTO> findById(@PathVariable Long id) {
        MainEvent mainEvent = mainEventService.findById(id);

        return ResponseEntity.ok(EventMapper.convertEntityToDTO(mainEvent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        MainEvent mainEvent = mainEventService.findById(id);
        mainEventService.delete(mainEvent);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/participant")
    public ResponseEntity<Void> subscribeParticipant(@PathVariable Long id, @RequestBody Long participantId){
        EventParticipant participant = eventParticipantService.findById(participantId);

        mainEventService.subscribeParticipant(id, participant);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/participant/{participantId}")
    public ResponseEntity<Void> unsubscribeParticipant(@PathVariable Long id, @PathVariable Long participantId){
        EventParticipant participant = eventParticipantService.findById(participantId);

        mainEventService.unsubscribeParticipant(id, participant);

        return ResponseEntity.noContent().build();
    }
}
