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

    @NotNull
    @ManyToOne
    @JoinColumn(name = "main_event_id", nullable = false)
    private MainEvent mainEvent;

    @ManyToMany
    @JoinTable(
            name = "main_event_action_event_participant",
            joinColumns = @JoinColumn(name = "main_event_action_id"),
            inverseJoinColumns = @JoinColumn(name = "event_participant_id")
    )
    private List<EventParticipant> eventParticipants;
}
