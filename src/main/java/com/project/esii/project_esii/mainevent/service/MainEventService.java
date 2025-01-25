package com.project.esii.project_esii.mainevent.service;

import com.project.esii.project_esii.eventmanager.domain.entity.EventManager;
import com.project.esii.project_esii.eventparticipant.domain.entity.EventParticipant;
import com.project.esii.project_esii.exceptions.domain.EmailNotVerifiedException;
import com.project.esii.project_esii.exceptions.domain.EntityNotFoundExcpetion;
import com.project.esii.project_esii.exceptions.domain.EventActionNotValidForEventException;
import com.project.esii.project_esii.mainevent.domain.dto.MainEventDTO;
import com.project.esii.project_esii.mainevent.domain.dto.MainEventFormDTO;
import com.project.esii.project_esii.mainevent.domain.entity.MainEvent;
import com.project.esii.project_esii.mainevent.mapper.EventMapper;
import com.project.esii.project_esii.mainevent.repository.MainEventRepository;
import com.project.esii.project_esii.maineventaction.domain.entity.MainEventAction;
import com.project.esii.project_esii.maineventtype.domain.entity.MainEventType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MainEventService {

    private final MainEventRepository mainEventRepository;

    public Page<MainEventDTO> findAll(Pageable pageable) {
        return mainEventRepository.findAll(pageable).map(EventMapper::convertEntityToDTO);
    }

    public MainEvent save(MainEventFormDTO mainEventFormDTO, EventManager eventManager, MainEventType mainEventType) {
        MainEvent mainEvent = EventMapper.convertFormToEntity(mainEventFormDTO, mainEventType, eventManager);

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
        MainEvent updatedMainEvent = EventMapper.convertFormToEntity(mainEventFormDTO, mainEventType, eventManager);
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

    public Page<MainEventDTO> findAllByEventManager(EventManager eventManager, Pageable pageable) {
        return mainEventRepository.findAllByEventManager(eventManager, pageable).map(EventMapper::convertEntityToDTO);
    }

    public MainEvent getOrNull(Long id) {
        if(id == null) return null;
        return mainEventRepository.findById(id).orElse(null);
    }

    public void verifyIfEventHasMainEventAction(MainEvent mainEvent, MainEventAction mainEventAction) {
        if(!mainEvent.getMainEventActionList().contains(mainEventAction)) {
            throw new EventActionNotValidForEventException();
        }
    }
}
