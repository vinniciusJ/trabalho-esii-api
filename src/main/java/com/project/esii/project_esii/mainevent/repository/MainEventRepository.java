package com.project.esii.project_esii.mainevent.repository;

import com.project.esii.project_esii.mainevent.domain.entity.MainEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainEventRepository extends JpaRepository<MainEvent, Long> {
}
