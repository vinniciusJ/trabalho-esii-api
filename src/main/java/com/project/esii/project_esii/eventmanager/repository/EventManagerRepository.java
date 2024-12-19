package com.project.esii.project_esii.eventmanager.repository;

import com.project.esii.project_esii.eventmanager.domain.entity.EventManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventManagerRepository extends JpaRepository<EventManager, Long> {
    EventManager findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT em FROM EventManager em WHERE em.cpf.cpfNumber = :cpfNumber")
    EventManager findByCpfNumber(@Param("cpfNumber") String cpfNumber);
}
