package com.project.esii.project_esii.eventmanager.domain.dto;

import com.project.esii.project_esii.enums.PersonRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EventManagerFormDTO(

        @NotBlank
        String cpfNumber,

        @NotBlank
        String name,

        @NotBlank
        String phone,

        @NotBlank
        String email,

        @NotBlank
        String password,

        @NotNull
        boolean isEmailVerified,

        @NotNull
        PersonRole personRole
) {
}
