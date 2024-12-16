package com.project.esii.project_esii.event_type.service;

import com.project.esii.project_esii.event_type.domain.dto.EventTypeDTO;
import com.project.esii.project_esii.event_type.domain.entity.EventType;
import com.project.esii.project_esii.event_type.repository.EventTypeRepository;
import com.project.esii.project_esii.event_type.mapper.EventTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class EventTypeService {
    private final EventTypeRepository eventTypeRepository;

    public EventType create(String name){
        return eventTypeRepository.save(EventTypeMapper.convertFormToEventType(name));
    }

    public void update(Long id, String name){
        verifyEventTypeExistsById(id);

        eventTypeRepository.save(EventTypeMapper.convertFormToEventType(id, name));
    }

    public Page<EventTypeDTO> findAll(Pageable pageable){
        return eventTypeRepository.findAll(pageable).map(EventTypeMapper::convertToDTO);
    }

    public EventTypeDTO findById(Long id){
        return EventTypeMapper.convertToDTO(getOrElseThrow(id));
    }

    public URI createURI(UriComponentsBuilder uriBuilder, EventType eventType){
        return uriBuilder.path("/event-type/{id}").buildAndExpand(eventType.getId()).toUri();
    }

    private void verifyEventTypeExistsById(Long id){
        if(!eventTypeRepository.existsById(id)){
            throw new RuntimeException("Não foi possível encontrar tipo de evento com ID" + id);
        }
    }

    private EventType getOrElseThrow(Long id){
        return eventTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar tipo de evento com ID" + id));
    }
}
