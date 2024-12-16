package com.project.esii.project_esii.user.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@AllArgsConstructor @NoArgsConstructor
@Data @ToString @Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String cpf;

    private String password;

    @Column(unique = true)
    private String email;

    private String phone;

    private Boolean isEmailVerified = false;
}
