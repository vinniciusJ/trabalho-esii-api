package com.project.esii.project_esii.eventsubscription.controller;

import com.project.esii.project_esii.eventparticipant.domain.entity.EventParticipant;
import com.project.esii.project_esii.eventparticipant.service.EventParticipantService;
import com.project.esii.project_esii.eventsubscription.domain.dto.EventSubscriptionDetailsDTO;
import com.project.esii.project_esii.eventsubscription.domain.dto.EventSubscriptionFormDTO;
import com.project.esii.project_esii.eventsubscription.domain.entity.EventSubscription;
import com.project.esii.project_esii.eventsubscription.service.EventSubscriptionService;
import com.project.esii.project_esii.mainevent.domain.entity.MainEvent;
import com.project.esii.project_esii.mainevent.service.MainEventService;
import com.project.esii.project_esii.maineventaction.domain.entity.MainEventAction;
import com.project.esii.project_esii.maineventaction.service.MainEventActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event-subscription")
@RequiredArgsConstructor
public class EventSubscriptionController {

    private final EventSubscriptionService eventSubscriptionService;
    private final MainEventService mainEventService;
    private final EventParticipantService eventParticipantService;
    private final MainEventActionService mainEventActionService;

    @PostMapping
    public ResponseEntity<EventSubscriptionDetailsDTO> subscribeToEvent(@RequestBody EventSubscriptionFormDTO eventSubscriptionFormDTO) {
        MainEvent mainEvent = mainEventService.findById(eventSubscriptionFormDTO.mainEventId());
        EventParticipant eventParticipant = eventParticipantService.findByCpfNumber(eventSubscriptionFormDTO.eventParticipantCpf());

        eventSubscriptionService.verifyIfAlreadyExistsSubscription(mainEvent, eventParticipant);

        EventSubscription eventSubscription = eventSubscriptionService.save(mainEvent, eventParticipant);

        return ResponseEntity.status(HttpStatus.CREATED).body(eventSubscriptionService.convertEventSubscriptionToEventSubscriptionDetailsDTO(eventSubscription));
    }

    @PutMapping("/action/{id}")
    public ResponseEntity<EventSubscriptionDetailsDTO> subscribeToEventAction(@PathVariable Long id, Long mainEventActionId) {
        EventSubscription eventSubscription = eventSubscriptionService.findById(id);
        MainEventAction mainEventAction = mainEventActionService.findById(mainEventActionId);

        mainEventService.verifyIfHasMainEventAction(eventSubscription.getMainEvent(), mainEventAction);

        return ResponseEntity.ok(eventSubscriptionService.update(eventSubscription, mainEventAction));
    }


}
