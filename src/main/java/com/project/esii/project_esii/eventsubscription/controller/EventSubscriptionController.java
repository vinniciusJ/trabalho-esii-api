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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @PostMapping("/action")
    public ResponseEntity<EventSubscriptionDetailsDTO> subscribeToEventAction(@RequestBody EventSubscriptionFormDTO eventSubscriptionFormDTO) {
        MainEvent mainEvent = mainEventService.findById(eventSubscriptionFormDTO.mainEventId());
        EventParticipant eventParticipant = eventParticipantService.findByCpfNumber(eventSubscriptionFormDTO.eventParticipantCpf());
        EventSubscription eventSubscription = eventSubscriptionService.findByMainEventAndEventParticipant(mainEvent, eventParticipant);
        MainEventAction mainEventAction = mainEventActionService.findById(eventSubscriptionFormDTO.mainEventId());

        mainEventService.verifyIfEventHasMainEventAction(eventSubscription.getMainEvent(), mainEventAction);
        mainEventActionService.verifyIfMainEventActionHasVacancies(mainEventAction);
        mainEventActionService.verifyIfAlreadyExistsSubscription(eventSubscription, mainEventAction);

        mainEventActionService.removeVacancyFromMainEventAction(mainEventAction);

        return ResponseEntity.ok(eventSubscriptionService.subscribeToMainEventAction(eventSubscription, mainEventAction));
    }

    @GetMapping("/{cpfNumber}")
    public ResponseEntity<Page<EventSubscriptionDetailsDTO>> getEventSubscriptionByCpfNumber(@PathVariable String cpfNumber, Pageable pageable) {
        EventParticipant eventParticipant = eventParticipantService.findByCpfNumber(cpfNumber);
        Page<EventSubscription> eventSubscriptionPage = eventSubscriptionService.findAllByEventParticipant(eventParticipant, pageable);
        return ResponseEntity.ok(eventSubscriptionService.convertEventSubscriptionPageToEventSubscriptionDetailsDTOPage(eventSubscriptionPage));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelEventSubscription(@PathVariable Long id) {
        EventSubscription eventSubscription = eventSubscriptionService.findById(id);
        eventSubscriptionService.delete(eventSubscription);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/action")
    public ResponseEntity<Void> cancelEventSubscription(@PathVariable Long id, Long mainEventActionId) {
        EventSubscription eventSubscription = eventSubscriptionService.findById(id);
        MainEventAction mainEventAction = mainEventActionService.findById(mainEventActionId);

        eventSubscriptionService.verifyIfEventSubscriptionHasMainEventAction(eventSubscription, mainEventAction);

        eventSubscriptionService.delete(eventSubscription, mainEventAction);

        return ResponseEntity.noContent().build();
    }

}
