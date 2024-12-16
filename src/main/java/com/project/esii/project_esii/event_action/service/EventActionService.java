package com.project.esii.project_esii.event_action.service;

import com.project.esii.project_esii.event.domain.entity.Event;
import com.project.esii.project_esii.event_action.domain.dto.EventActionDTO;
import com.project.esii.project_esii.event_action.domain.dto.EventActionFormDTO;
import com.project.esii.project_esii.event_action.domain.entity.EventAction;
import com.project.esii.project_esii.event_action.mapper.EventActionMapper;
import com.project.esii.project_esii.event_action.repository.EventActionRepository;
import com.project.esii.project_esii.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventActionService {
    private final EventActionRepository eventActionRepository;

    public EventAction create(EventActionFormDTO eventAction, Event event, User responsible) {
        return eventActionRepository.save(EventActionMapper.convertFormToEntity(eventAction, event, responsible));
    }

    public Page<EventActionDTO> findAll(Pageable pageable) {
        return eventActionRepository.findAll(pageable).map(EventActionMapper::convertEntityToDTO);
    }

    public void registerParticipant(Long eventActionId, Long userId) {
        verifyEventActionsExistsById(eventActionId);

        eventActionRepository.decrementAvailablePositions(eventActionId);
        eventActionRepository.addParticipant(eventActionId, userId);
    }

    private void verifyEventActionsExistsById(Long eventActionId) {
        if(!eventActionRepository.existsById(eventActionId)) {
            throw new RuntimeException("Não foi possível encontrar ação de evento com ID " + eventActionId);
        }
    }

    private EventAction getOrElseThrow(Long id) {
        return eventActionRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar ação de evento com ID " + id));
    }

}
