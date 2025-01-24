package com.project.esii.project_esii.mainevent.service;

import com.project.esii.project_esii.eventmanager.domain.entity.EventManager;
import com.project.esii.project_esii.exceptions.type.EntityNotFoundExcpetion;
import com.project.esii.project_esii.exceptions.type.EventActionNotValidForEventException;
import com.project.esii.project_esii.mainevent.domain.dto.MainEventDetailsDTO;
import com.project.esii.project_esii.mainevent.domain.dto.MainEventFormDTO;
import com.project.esii.project_esii.mainevent.domain.entity.MainEvent;
import com.project.esii.project_esii.mainevent.mapper.MainEventMapper;
import com.project.esii.project_esii.mainevent.repository.MainEventRepository;
import com.project.esii.project_esii.maineventaction.domain.entity.MainEventAction;
import com.project.esii.project_esii.maineventtype.domain.entity.MainEventType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainEventService {

    private final MainEventRepository mainEventRepository;
    private final MainEventMapper mainEventMapper;

    public MainEvent save(MainEventFormDTO mainEventFormDTO, EventManager eventManager, MainEventType mainEventType) {
        MainEvent mainEvent = convertMainEventFormDTOToMainEvent(mainEventFormDTO, eventManager, mainEventType);

        return mainEventRepository.save(mainEvent);
    }

    private MainEvent convertMainEventFormDTOToMainEvent(MainEventFormDTO mainEventFormDTO, EventManager eventManager, MainEventType mainEventType) {
        return mainEventMapper.convertMainEventFormDTOToMainEvent(mainEventFormDTO, eventManager, mainEventType);
    }

    public MainEventDetailsDTO convertMainEventToMainEventDetailsDTO(MainEvent mainEvent) {
        return mainEventMapper.convertMainEventToMainEventDetailsDTO(mainEvent);
    }

    public Page<MainEvent> findAll(Pageable pageable) {
        return mainEventRepository.findAll(pageable);
    }

    public Page<MainEventDetailsDTO> convertToMainEventDetailsDTOPage(Page<MainEvent> mainEventPage) {
        return mainEventPage.map(mainEventMapper::convertMainEventToMainEventDetailsDTO);
    }

    public MainEvent findById(Long id) {
        return mainEventRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExcpetion("MainEvent", "id", id.toString())
        );
    }

    public void delete(MainEvent mainEvent) {
        mainEventRepository.delete(mainEvent);
    }

    public MainEventDetailsDTO update(MainEvent mainEvent, EventManager eventManager, MainEventType mainEventType, MainEventFormDTO mainEventFormDTO) {
        MainEvent updatedMainEvent = convertMainEventFormDTOToMainEvent(mainEventFormDTO, eventManager, mainEventType);
        updatedMainEvent.setId(mainEvent.getId());

        mainEventRepository.save(updatedMainEvent);

        return convertMainEventToMainEventDetailsDTO(updatedMainEvent);
    }

    public Page<MainEvent> findAllByEventManager(EventManager eventManager, Pageable pageable) {
        return mainEventRepository.findAllByEventManager(eventManager, pageable);
    }

    public MainEvent getOrNull(Long id) {
        if(id == null) return null;
        return mainEventRepository.findById(id).orElse(null);
    }

    public void verifyIfHasMainEventAction(MainEvent mainEvent, MainEventAction mainEventAction) {
        if(!mainEvent.getMainEventActionList().contains(mainEventAction)) {
            throw new EventActionNotValidForEventException();
        }

    }
}
