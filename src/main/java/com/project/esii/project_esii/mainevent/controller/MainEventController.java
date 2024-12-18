package com.project.esii.project_esii.mainevent.controller;

import com.project.esii.project_esii.eventmanager.domain.entity.EventManager;
import com.project.esii.project_esii.eventmanager.service.EventManagerService;
import com.project.esii.project_esii.mainevent.domain.dto.MainEventDetailsDTO;
import com.project.esii.project_esii.mainevent.domain.dto.MainEventFormDTO;
import com.project.esii.project_esii.mainevent.domain.entity.MainEvent;
import com.project.esii.project_esii.mainevent.service.MainEventService;
import com.project.esii.project_esii.maineventtype.domain.entity.MainEventType;
import com.project.esii.project_esii.maineventtype.domain.service.MainEventTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main-event")
@RequiredArgsConstructor
public class MainEventController {

    private final MainEventService mainEventService;
    private final MainEventTypeService mainEventTypeService;
    private final EventManagerService eventManagerService;

    public ResponseEntity<MainEventDetailsDTO> create(@RequestBody MainEventFormDTO mainEventFormDTO) {
        MainEventType mainEventType = mainEventTypeService.findById(mainEventFormDTO.mainEventTypeId());
        EventManager eventManager = eventManagerService.findById(mainEventFormDTO.eventManagerId());

        MainEvent mainEvent = mainEventService.save(mainEventFormDTO, eventManager, mainEventType);

        return ResponseEntity.status(HttpStatus.CREATED).body(mainEventService.convertMainEventToMainEventDetailsDTO(mainEvent));
    }
}
