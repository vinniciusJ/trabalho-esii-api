package com.project.esii.project_esii.event_type.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "event_type")
@AllArgsConstructor @NoArgsConstructor
@Data @ToString @Builder
public class EventType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
