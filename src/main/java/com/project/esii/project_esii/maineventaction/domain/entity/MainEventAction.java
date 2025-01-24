package com.project.esii.project_esii.maineventaction.domain.entity;

import com.project.esii.project_esii.activity.domain.entity.Activity;
import com.project.esii.project_esii.eventparticipant.domain.entity.EventParticipant;
import com.project.esii.project_esii.mainevent.domain.entity.MainEvent;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@Table(name = "main_event_action")
@EqualsAndHashCode(callSuper = true)
public class MainEventAction extends Activity {

    @NotNull
    @Column(nullable = false)
    private Integer quantityVacancies;

    @OneToMany(mappedBy = "mainEventAction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventParticipant> participants;  // Relacionamento com participantes

    @NotNull
    @ManyToOne
    @JoinColumn(name = "main_event_id", nullable = false)
    private MainEvent mainEvent;  // Relacionamento com MainEvent
}
