package com.project.esii.project_esii.eventparticipant.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EventParticipantDetailsDTO(

        @NotNull
        Long id,

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
        boolean isEmailVerified
) {
}
