package com.project.esii.project_esii.maineventaction.repository;

import com.project.esii.project_esii.maineventaction.domain.entity.MainEventAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainEventActionRepository extends JpaRepository<MainEventAction, Long> {
}
