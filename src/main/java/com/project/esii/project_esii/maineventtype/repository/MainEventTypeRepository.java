package com.project.esii.project_esii.maineventtype.repository;

import com.project.esii.project_esii.maineventtype.domain.entity.MainEventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainEventTypeRepository extends JpaRepository<MainEventType, Long> {
}
