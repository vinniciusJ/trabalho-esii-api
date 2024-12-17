package com.project.esii.project_esii.eventmanager.domain.entity;

import com.project.esii.project_esii.naturalperson.domain.entity.NaturalPerson;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "event_manager")
public class EventManager extends NaturalPerson {

}
