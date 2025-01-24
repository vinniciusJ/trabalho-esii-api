package com.project.esii.project_esii.eventsubscription.domain.entity;

import com.project.esii.project_esii.eventparticipant.domain.entity.EventParticipant;
import com.project.esii.project_esii.mainevent.domain.entity.MainEvent;
import com.project.esii.project_esii.maineventaction.domain.entity.MainEventAction;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "event_subscription")
public class EventSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "event_participant_id", nullable = false)
    private EventParticipant eventParticipant;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "main_event_id", nullable = false)
    private MainEvent mainEvent;

    @NotNull
    @Column(nullable = false)
    @OneToMany
    private List<MainEventAction> mainEventActionList;
}
