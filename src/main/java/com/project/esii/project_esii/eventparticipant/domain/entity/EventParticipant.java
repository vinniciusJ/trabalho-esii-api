package com.project.esii.project_esii.eventparticipant.domain.entity;

import com.project.esii.project_esii.naturalperson.domain.entity.NaturalPerson;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "event_participant")
@EqualsAndHashCode(callSuper = true)
public class EventParticipant extends NaturalPerson {
}
