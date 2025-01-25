package com.project.esii.project_esii.maineventaction.repository;

import com.project.esii.project_esii.mainevent.domain.entity.MainEvent;
import com.project.esii.project_esii.maineventaction.domain.entity.MainEventAction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MainEventActionRepository extends JpaRepository<MainEventAction, Long>, JpaSpecificationExecutor<MainEventAction> {
    Page<MainEventAction> findAllByMainEvent(MainEvent mainEvent, Pageable pageable);
}
