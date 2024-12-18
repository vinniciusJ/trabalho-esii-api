package com.project.esii.project_esii.activity.domain.entity;

import com.project.esii.project_esii.eventmanager.domain.entity.EventManager;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String title;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime startDateTime;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime endDateTime;

    @NotNull
    @Column(nullable = false)
    private Double registrationPrice;

    @NotNull
    @Column(nullable = false)
    private String address;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "event_manager_id", nullable = false)
    private EventManager eventManager;
}
