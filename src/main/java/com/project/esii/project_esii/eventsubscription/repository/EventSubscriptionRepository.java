package com.project.esii.project_esii.eventsubscription.repository;

import com.project.esii.project_esii.eventparticipant.domain.entity.EventParticipant;
import com.project.esii.project_esii.eventsubscription.domain.entity.EventSubscription;
import com.project.esii.project_esii.mainevent.domain.entity.MainEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventSubscriptionRepository extends JpaRepository<EventSubscription, Long> {
    boolean existsByMainEventAndEventParticipant(MainEvent mainEvent, EventParticipant eventParticipant);
}
