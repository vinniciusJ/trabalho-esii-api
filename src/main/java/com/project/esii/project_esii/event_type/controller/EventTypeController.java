package com.project.esii.project_esii.event_type.controller;

import com.project.esii.project_esii.event_type.domain.dto.EventTypeDTO;
import com.project.esii.project_esii.event_type.domain.entity.EventType;
import com.project.esii.project_esii.event_type.service.EventTypeService;
import com.project.esii.project_esii.mappers.EventTypeMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("event-type")
@RequiredArgsConstructor
public class EventTypeController {
    private final EventTypeService eventTypeService;

    @GetMapping
    public ResponseEntity<Page<EventTypeDTO>> findAll(Pageable pageable) {
        Page<EventTypeDTO> eventTypes = eventTypeService.findAll(pageable);

        return ResponseEntity.ok(eventTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventTypeDTO> findById(@PathVariable Long id) {
        EventTypeDTO eventType = eventTypeService.findById(id);

        return ResponseEntity.ok(eventType);
    }

    @PostMapping
    public ResponseEntity<EventTypeDTO> create(@RequestBody String name, UriComponentsBuilder uriBuilder) {
        EventType eventType = eventTypeService.create(name);
        URI uri = eventTypeService.createURI(uriBuilder, eventType);

        return ResponseEntity.created(uri).body(EventTypeMapper.convertToDTO(eventType));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody String name) {
        eventTypeService.update(id, name);

        return ResponseEntity.noContent().build();
    }
}
