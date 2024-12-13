package com.project.esii.project_esii.user.domain.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public record UserDTO(
        Long id,
        String name,
        String email
) {
}

@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String name;

private String cpf;

private String password;

private String email;

private String phone;

private Boolean isEmailVerified = false;