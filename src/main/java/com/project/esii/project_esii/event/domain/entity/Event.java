package com.project.esii.project_esii.event.domain.entity;

import com.project.esii.project_esii.event_action.domain.entity.EventAction;
import com.project.esii.project_esii.event_type.domain.entity.EventType;
import com.project.esii.project_esii.user.domain.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "event")
@AllArgsConstructor @NoArgsConstructor
@Data @ToString @Builder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDate startDate;

    private LocalDate endDate;

    private Double registrationFee;

    private Integer availablePositions;

    @ManyToOne
    private EventType eventType;

    @ManyToOne
    private User responsible;

    @OneToMany
    private List<User> participants;

    @OneToMany(mappedBy = "event")
    private List<EventAction> eventActions;
}
