package com.project.esii.project_esii.mainevent.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.project.esii.project_esii.eventmanager.domain.entity.EventManager;
import com.project.esii.project_esii.mainevent.domain.entity.MainEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MainEventRepository extends JpaRepository<MainEvent, Long>, JpaSpecificationExecutor<MainEvent> {
    Page<MainEvent> findAllByEventManager(EventManager eventManager, Pageable pageable);
}
