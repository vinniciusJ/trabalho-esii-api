package com.project.esii.project_esii.event_action.entity;

import com.project.esii.project_esii.event.domain.entity.Event;
import com.project.esii.project_esii.user.domain.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "event_action")
@AllArgsConstructor @NoArgsConstructor
@Data @ToString @Builder
public class EventAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Double registrationFee;

    private Integer availablePositions;

    @ManyToOne
    private User responsible;

    @ManyToOne
    private Event event;

    @OneToMany
    private List<User> participants;
}
