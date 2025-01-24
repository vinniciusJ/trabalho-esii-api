package com.project.esii.project_esii.eventsubscription.service;

import com.project.esii.project_esii.eventparticipant.domain.entity.EventParticipant;
import com.project.esii.project_esii.eventsubscription.domain.dto.EventSubscriptionDetailsDTO;
import com.project.esii.project_esii.eventsubscription.domain.dto.EventSubscriptionFormDTO;
import com.project.esii.project_esii.eventsubscription.domain.entity.EventSubscription;
import com.project.esii.project_esii.eventsubscription.mapper.EventSubscriptionMapper;
import com.project.esii.project_esii.eventsubscription.repository.EventSubscriptionRepository;
import com.project.esii.project_esii.exceptions.type.EntityNotFoundExcpetion;
import com.project.esii.project_esii.exceptions.type.ExistingEventSubscriptionException;
import com.project.esii.project_esii.mainevent.domain.entity.MainEvent;
import com.project.esii.project_esii.maineventaction.domain.entity.MainEventAction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventSubscriptionService {

    private final EventSubscriptionRepository eventSubscriptionRepository;
    private final EventSubscriptionMapper eventSubscriptionMapper;

    public void verifyIfAlreadyExistsSubscription(MainEvent mainEvent, EventParticipant eventParticipant) {
        if(eventSubscriptionRepository.existsByMainEventAndEventParticipant(mainEvent, eventParticipant)) {
            throw new ExistingEventSubscriptionException();
        }
    }

    public EventSubscription save(MainEvent mainEvent, EventParticipant eventParticipant) {
        EventSubscription eventSubscription = new EventSubscription();
        eventSubscription.setMainEvent(mainEvent);
        eventSubscription.setEventParticipant(eventParticipant);

        return eventSubscriptionRepository.save(eventSubscription);
    }

    public EventSubscriptionDetailsDTO convertEventSubscriptionToEventSubscriptionDetailsDTO(EventSubscription eventSubscription) {
        return eventSubscriptionMapper.convertEventSubscriptionToEventSubscriptionDetailsDTO(eventSubscription);
    }

    public EventSubscription findById(Long id) {
        return eventSubscriptionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExcpetion("EventSubscription", "id", id.toString())
        );
    }

    public EventSubscriptionDetailsDTO update(EventSubscription eventSubscription, MainEventAction mainEventAction) {
        if(eventSubscription.getMainEvent().getMainEventActionList() == null) {
            eventSubscription.setMainEventActionList(new ArrayList<>(List.of(mainEventAction)));
        } else {
            eventSubscription.getMainEventActionList().add(mainEventAction);
        }

        EventSubscription eventSubscriptionUpdated = eventSubscriptionRepository.save(eventSubscription);
        return convertEventSubscriptionToEventSubscriptionDetailsDTO(eventSubscriptionUpdated);
    }
}
