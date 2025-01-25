package com.project.esii.project_esii.mainevent.service;

import com.project.esii.project_esii.eventmanager.domain.entity.EventManager;
import com.project.esii.project_esii.eventparticipant.domain.entity.EventParticipant;
import com.project.esii.project_esii.exceptions.domain.EmailNotVerifiedException;
import com.project.esii.project_esii.exceptions.domain.EntityNotFoundExcpetion;
import com.project.esii.project_esii.mainevent.domain.dto.MainEventDTO;
import com.project.esii.project_esii.mainevent.domain.dto.MainEventFiltersDTO;
import com.project.esii.project_esii.mainevent.domain.dto.MainEventFormDTO;
import com.project.esii.project_esii.mainevent.domain.entity.MainEvent;
import com.project.esii.project_esii.mainevent.mapper.EventMapper;
import com.project.esii.project_esii.mainevent.repository.MainEventRepository;
import com.project.esii.project_esii.maineventaction.domain.entity.MainEventAction;
import com.project.esii.project_esii.maineventtype.domain.entity.MainEventType;
import com.project.esii.project_esii.specification.BaseSpecification;
import com.project.esii.project_esii.specification.Search;
import com.project.esii.project_esii.specification.SpecificationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MainEventService {

    private final MainEventRepository mainEventRepository;

    public Page<MainEventDTO> findAll(MainEventFiltersDTO filters, Pageable pageable) {
        Specification<MainEvent> mainEventSpecification = generateSpecification(filters);

        return mainEventRepository.findAll(mainEventSpecification, pageable).map(EventMapper::convertEntityToDTO);
    }

    public MainEvent save(MainEventFormDTO mainEventFormDTO, EventManager eventManager, MainEventType mainEventType) {
        MainEvent mainEvent = EventMapper.convertFormToEntity(null, mainEventFormDTO, mainEventType, eventManager);

        return mainEventRepository.save(mainEvent);
    }

    public MainEvent findById(Long id) {
        return mainEventRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExcpetion("MainEvent", "id", id.toString())
        );
    }

    public void delete(MainEvent mainEvent) {
        mainEventRepository.delete(mainEvent);
    }

    public MainEventDTO update(MainEvent mainEvent, EventManager eventManager, MainEventType mainEventType, MainEventFormDTO mainEventFormDTO) {
        MainEvent updatedMainEvent = EventMapper.convertFormToEntity(mainEvent, mainEventFormDTO, mainEventType, eventManager);
        updatedMainEvent.setId(mainEvent.getId());

        mainEventRepository.save(updatedMainEvent);

        return EventMapper.convertEntityToDTO(updatedMainEvent);
    }

    public void subscribeParticipant(Long id, EventParticipant participant){
        MainEvent mainEvent = findById(id);

        if(Objects.equals(participant.getIsEmailVerified(), false)){
            throw new EmailNotVerifiedException();
        }

        mainEvent.getEventParticipants().add(participant);

        mainEventRepository.save(mainEvent);
    }

    public void unsubscribeParticipant(Long id, EventParticipant participant){
        MainEvent mainEvent = findById(id);

        List<MainEventAction> actions = mainEvent.getMainEventActionList();
        List<EventParticipant> participants = mainEvent.getEventParticipants();

        participants.remove(participant);

        for(MainEventAction action : actions){
            List<EventParticipant> actionParticipants = action.getEventParticipants();

            actionParticipants.remove(participant);
            action.setEventParticipants(actionParticipants);
        }

        mainEvent.setEventParticipants(participants);
        mainEvent.setMainEventActionList(actions);

        mainEventRepository.save(mainEvent);
    }

    private Specification<MainEvent> generateSpecification(MainEventFiltersDTO filters){
        Search<Long> eventTypeIdCriteria = SpecificationUtils.generateEqualsCriteria("mainEventType.id", filters.eventTypeId());
        Search<Long> eventManagerCriteria = SpecificationUtils.generateEqualsCriteria("eventManager.id", filters.eventManagerId());

        Specification<MainEvent> eventTypeSpecification = new BaseSpecification<>(eventTypeIdCriteria);
        Specification<MainEvent> eventManagerSpecification = new BaseSpecification<>(eventManagerCriteria);

        return Specification.where(eventTypeSpecification.and(eventManagerSpecification));
    }
}
