package com.project.esii.project_esii.eventparticipant.repository;

import com.project.esii.project_esii.eventparticipant.domain.entity.EventParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventParticipantRepository extends JpaRepository<EventParticipant, Long> {
    EventParticipant findByEmail(String email);
}
