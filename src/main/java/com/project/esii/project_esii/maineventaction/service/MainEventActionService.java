package com.project.esii.project_esii.maineventaction.service;

import com.project.esii.project_esii.eventmanager.domain.entity.EventManager;
import com.project.esii.project_esii.eventparticipant.domain.entity.EventParticipant;
import com.project.esii.project_esii.exceptions.domain.*;
import com.project.esii.project_esii.mainevent.domain.dto.MainEventFiltersDTO;
import com.project.esii.project_esii.mainevent.domain.entity.MainEvent;
import com.project.esii.project_esii.maineventaction.domain.dto.MainEventActionDTO;
import com.project.esii.project_esii.maineventaction.domain.dto.MainEventActionFiltersDTO;
import com.project.esii.project_esii.maineventaction.domain.dto.MainEventActionFormDTO;
import com.project.esii.project_esii.maineventaction.domain.entity.MainEventAction;
import com.project.esii.project_esii.maineventaction.mapper.EventActionMapper;
import com.project.esii.project_esii.maineventaction.repository.MainEventActionRepository;
import com.project.esii.project_esii.specification.BaseSpecification;
import com.project.esii.project_esii.specification.Search;
import com.project.esii.project_esii.specification.SpecificationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MainEventActionService {

    private final MainEventActionRepository mainEventActionRepository;

    public MainEventAction save(MainEventActionFormDTO mainEventActionFormDTO, MainEvent mainEvent, EventManager eventManager) {
        MainEventAction mainEventAction = EventActionMapper.convertFormToEntity(mainEventActionFormDTO, eventManager, mainEvent);

        return mainEventActionRepository.save(mainEventAction);
    }

    public Page<MainEventActionDTO> findAll(MainEventActionFiltersDTO filters, Pageable pageable) {
        Specification<MainEventAction> mainEventActionSpecification = generateSpecification(filters);

        return mainEventActionRepository.findAll(mainEventActionSpecification, pageable).map(EventActionMapper::convertEntityToDTO);
    }

    public void subscribeParticipant(Long id, EventParticipant participant){
        MainEventAction action = findById(id);
        MainEvent event = action.getMainEvent();

        if(!event.getMainEventActionList().contains(action)){
            throw new ActionNotExistsByEventException(action.getTitle(), event.getTitle());
        }

        if(action.getEventParticipants().size() == action.getQuantityVacancies()){
            throw new VacancyLimitReachedException(action.getQuantityVacancies());
        }

        if(action.getEventParticipants().contains(participant)){
            throw new ExistingEventSubscriptionException();
        }

        action.getEventParticipants().add(participant);

        mainEventActionRepository.save(action);
    }

    public void unsubscribeParticipant(Long id, EventParticipant participant){
        MainEventAction action = findById(id);
        List<EventParticipant> participants = action.getEventParticipants();

        participants.remove(participant);
        action.setEventParticipants(participants);

        mainEventActionRepository.save(action);
    }

    public MainEventAction findById(Long id) {
        return mainEventActionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExcpetion("MainEventAction", "id", id.toString())
        );
    }

    public void delete(MainEventAction mainEventAction) {
        mainEventActionRepository.delete(mainEventAction);
    }

    private Specification<MainEventAction> generateSpecification(MainEventActionFiltersDTO filters){
        Search<Long> eventIdCriteria = SpecificationUtils.generateEqualsCriteria("mainEvent.id", filters.eventId());
        Search<Long> eventManagerCriteria = SpecificationUtils.generateEqualsCriteria("eventManager.id", filters.eventManagerId());

        Specification<MainEventAction> eventSpecification = new BaseSpecification<>(eventIdCriteria);
        Specification<MainEventAction> eventManagerSpecification = new BaseSpecification<>(eventManagerCriteria);

        return Specification.where(eventSpecification.and(eventManagerSpecification));
    }
}
