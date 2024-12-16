package com.project.esii.project_esii.event_action.repository;

import com.project.esii.project_esii.event_action.domain.entity.EventAction;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface EventActionRepository extends JpaRepository<EventAction, Long> {
    @NotNull
    Page<EventAction> findAll(@NotNull Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE EventAction ea SET ea.availablePositions = ea.availablePositions - 1 " +
            "WHERE ea.id = :eventActionId AND ea.availablePositions > 0")
    void decrementAvailablePositions(Long eventActionId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO event_action_participants (event_action_id, user_id) VALUES (:eventActionId, :userId)", nativeQuery = true)
    void addParticipant(Long eventActionId, Long userId);
}
