package com.project.esii.project_esii.event_type.repository;

import com.project.esii.project_esii.event_type.domain.entity.EventType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTypeRepository extends JpaRepository<EventType, Long> {
    Page<EventType> findAll(Pageable pageable);
}
