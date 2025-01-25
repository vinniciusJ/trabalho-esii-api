package com.project.esii.project_esii.mainevent.domain.entity;

import com.project.esii.project_esii.activity.domain.entity.Activity;
import com.project.esii.project_esii.eventparticipant.domain.entity.EventParticipant;
import com.project.esii.project_esii.maineventaction.domain.entity.MainEventAction;
import com.project.esii.project_esii.maineventtype.domain.entity.MainEventType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "main_event")
@AllArgsConstructor @NoArgsConstructor
@Data @EqualsAndHashCode(callSuper = true)
public class MainEvent extends Activity {

    @ManyToOne
    @JoinColumn(name = "main_event_type_id", nullable = false)
    private MainEventType mainEventType;

    @OneToMany(mappedBy = "mainEvent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MainEventAction> mainEventActionList;

    @ManyToMany
    @JoinTable(
            name = "main_event_event_participant",
            joinColumns = @JoinColumn(name = "main_event_id"),
            inverseJoinColumns = @JoinColumn(name = "event_participant_id")
    )
    private List<EventParticipant> eventParticipants;
}
