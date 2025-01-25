package com.project.esii.project_esii.maineventaction.service;

import com.project.esii.project_esii.eventmanager.domain.entity.EventManager;
import com.project.esii.project_esii.eventparticipant.domain.entity.EventParticipant;
import com.project.esii.project_esii.eventsubscription.domain.entity.EventSubscription;
import com.project.esii.project_esii.exceptions.type.EntityNotFoundExcpetion;
import com.project.esii.project_esii.exceptions.type.ExistingEventSubscriptionException;
import com.project.esii.project_esii.exceptions.type.NoVacancyForMainEventActionException;
import com.project.esii.project_esii.mainevent.domain.entity.MainEvent;
import com.project.esii.project_esii.maineventaction.domain.dto.MainEventActionDetailsDTO;
import com.project.esii.project_esii.maineventaction.domain.dto.MainEventActionFormDTO;
import com.project.esii.project_esii.maineventaction.domain.entity.MainEventAction;
import com.project.esii.project_esii.maineventaction.mapper.MainEventActionMapper;
import com.project.esii.project_esii.maineventaction.repository.MainEventActionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainEventActionService {

    private final MainEventActionRepository mainEventActionRepository;
    private final MainEventActionMapper mainEventActionMapper;

    public MainEventAction save(MainEventActionFormDTO mainEventActionFormDTO, MainEvent mainEvent, EventManager eventManager) {
        MainEventAction mainEventAction = convertMainEventActionFormDTOToMainEventAction(mainEventActionFormDTO, mainEvent, eventManager);

        return mainEventActionRepository.save(mainEventAction);
    }

    private MainEventAction convertMainEventActionFormDTOToMainEventAction(MainEventActionFormDTO mainEventActionFormDTO, MainEvent mainEvent, EventManager eventManager) {
        return mainEventActionMapper.convertMainEventActionFormDTOToMainEventAction(mainEventActionFormDTO, mainEvent, eventManager);
    }

    public MainEventActionDetailsDTO convertMainEventActionToMainEventActionDetailsDTO(MainEventAction mainEventAction) {
        return mainEventActionMapper.convertMainEventActionToMainEventActionDetailsDTO(mainEventAction);
    }

    public Page<MainEventAction> findAll(Pageable pageable) {
        return mainEventActionRepository.findAll(pageable);
    }

    public Page<MainEventActionDetailsDTO> convertToMainEventActionDetailsDTOPage(Page<MainEventAction> mainEventActionPage) {
        return mainEventActionPage.map(this::convertMainEventActionToMainEventActionDetailsDTO);
    }


//    public void subscribeParticipant(Long id, EventParticipant eventParticipant, MainEvent event) {
//        MainEventAction action = findById(id);
//
//        if(!event.getMainEventActionList().contains(action)){
//            throw new RuntimeException("Ação " + action.getTitle() + " não existe para o evento " + event.getTitle());
//        }
//
//        if(action.getParticipants().size() + 1 > action.getQuantityVacancies()){
//            throw new RuntimeException("Limites de vagas excedido para a ação " + action.getTitle() + " no evento " + action.getMainEvent().getTitle());
//        }
//
//        action.getParticipants().add(eventParticipant);
//        action.setQuantityVacancies(action.getQuantityVacancies() + 1);
//
//        mainEventActionRepository.save(action);
//    }

    public MainEventAction findById(Long id) {
        return mainEventActionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExcpetion("MainEventAction", "id", id.toString())
        );
    }

    public void delete(MainEventAction mainEventAction) {
        mainEventActionRepository.delete(mainEventAction);
    }

    public Page<MainEventAction> findAllByMainEvent(MainEvent mainEvent, Pageable pageable) {
        return mainEventActionRepository.findAllByMainEvent(mainEvent, pageable);
    }

    public void verifyIfMainEventActionHasVacancies(MainEventAction mainEventAction) {
        if(mainEventAction.getQuantityVacancies() == 0) {
            throw new NoVacancyForMainEventActionException(mainEventAction.getId().toString());
        }
    }

    public void verifyIfAlreadyExistsSubscription(EventSubscription eventSubscription, MainEventAction mainEventAction) {
        if(eventSubscription.getMainEventActionList().contains(mainEventAction)) {
            throw new ExistingEventSubscriptionException();
        }
    }

    public void removeVacancyFromMainEventAction(MainEventAction mainEventAction) {
        mainEventAction.setQuantityVacancies(mainEventAction.getQuantityVacancies() - 1);
        mainEventActionRepository.save(mainEventAction);
    }
}
