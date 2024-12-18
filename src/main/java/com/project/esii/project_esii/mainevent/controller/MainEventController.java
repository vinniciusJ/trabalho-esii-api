package com.project.esii.project_esii.mainevent.controller;

import com.project.esii.project_esii.eventmanager.domain.entity.EventManager;
import com.project.esii.project_esii.eventmanager.service.EventManagerService;
import com.project.esii.project_esii.mainevent.domain.dto.MainEventDetailsDTO;
import com.project.esii.project_esii.mainevent.domain.dto.MainEventFormDTO;
import com.project.esii.project_esii.mainevent.domain.entity.MainEvent;
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

    @PostMapping
    public ResponseEntity<MainEventDetailsDTO> create(@RequestBody MainEventFormDTO mainEventFormDTO) {
        MainEventType mainEventType = mainEventTypeService.findById(mainEventFormDTO.mainEventTypeId());
        EventManager eventManager = eventManagerService.findById(mainEventFormDTO.eventManagerId());

        MainEvent mainEvent = mainEventService.save(mainEventFormDTO, eventManager, mainEventType);

        return ResponseEntity.status(HttpStatus.CREATED).body(mainEventService.convertMainEventToMainEventDetailsDTO(mainEvent));
    }

    @GetMapping
    public ResponseEntity<Page<MainEventDetailsDTO>> list(Pageable pageable) {
        Page<MainEvent> mainEventPage = mainEventService.findAll(pageable);

        return ResponseEntity.ok(mainEventService.convertToMainEventDetailsDTOPage(mainEventPage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MainEventDetailsDTO> findById(@PathVariable Long id) {
        MainEvent mainEvent = mainEventService.findById(id);

        return ResponseEntity.ok(mainEventService.convertMainEventToMainEventDetailsDTO(mainEvent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        MainEvent mainEvent = mainEventService.findById(id);
        mainEventService.delete(mainEvent);

        return ResponseEntity.noContent().build();
    }
}
