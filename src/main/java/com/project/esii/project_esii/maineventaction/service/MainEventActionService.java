package com.project.esii.project_esii.maineventaction.service;

import com.project.esii.project_esii.eventmanager.domain.entity.EventManager;
import com.project.esii.project_esii.eventparticipant.domain.entity.EventParticipant;
import com.project.esii.project_esii.exceptions.domain.*;
import com.project.esii.project_esii.mainevent.domain.entity.MainEvent;
import com.project.esii.project_esii.maineventaction.domain.dto.MainEventActionDTO;
import com.project.esii.project_esii.maineventaction.domain.dto.MainEventActionFormDTO;
import com.project.esii.project_esii.maineventaction.domain.entity.MainEventAction;
import com.project.esii.project_esii.maineventaction.mapper.EventActionMapper;
import com.project.esii.project_esii.maineventaction.repository.MainEventActionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<MainEventActionDTO> findAll(Pageable pageable) {
        return mainEventActionRepository.findAll(pageable).map(EventActionMapper::convertEntityToDTO);
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

    public MainEventAction findById(Long id) {
        return mainEventActionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExcpetion("MainEventAction", "id", id.toString())
        );
    }

    public void delete(MainEventAction mainEventAction) {
        mainEventActionRepository.delete(mainEventAction);
    }

    public Page<MainEventActionDTO> findAllByMainEvent(MainEvent mainEvent, Pageable pageable) {
        return mainEventActionRepository.findAllByMainEvent(mainEvent, pageable).map(EventActionMapper::convertEntityToDTO);
    }

    public void verifyIfMainEventActionHasVacancies(MainEventAction mainEventAction) {
        if(mainEventAction.getQuantityVacancies() == 0) {
            throw new NoVacancyForMainEventActionException(mainEventAction.getId().toString());
        }
    }

//    public void verifyIfAlreadyExistsSubscription(EventSubscription eventSubscription, MainEventAction mainEventAction) {
//        if(eventSubscription.getMainEventActionList().contains(mainEventAction)) {
//            throw new ExistingEventSubscriptionException();
//        }
//    }

    public void removeVacancyFromMainEventAction(MainEventAction mainEventAction) {
        mainEventAction.setQuantityVacancies(mainEventAction.getQuantityVacancies() - 1);
        mainEventActionRepository.save(mainEventAction);
    }

    public void addVacancyToMainEventActions(List<MainEventAction> mainEventActionList) {
        for(MainEventAction mainEventAction : mainEventActionList) {
            mainEventAction.setQuantityVacancies(mainEventAction.getQuantityVacancies() + 1);
            mainEventActionRepository.save(mainEventAction);
        }
    }

    public void addVacancyToMainEventAction(MainEventAction mainEventAction) {
        mainEventAction.setQuantityVacancies(mainEventAction.getQuantityVacancies() + 1);
        mainEventActionRepository.save(mainEventAction);
    }
}
