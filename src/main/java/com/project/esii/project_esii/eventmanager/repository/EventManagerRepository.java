package com.project.esii.project_esii.eventmanager.repository;

import com.project.esii.project_esii.eventmanager.domain.entity.EventManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventManagerRepository extends JpaRepository<EventManager, Long> {
    EventManager findByEmail(String email);

    boolean existsByEmail(String email);
}
