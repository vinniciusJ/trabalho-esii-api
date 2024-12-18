package com.project.esii.project_esii.eventparticipant.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record EventParticipantFormDTO(

        @NotBlank
        String cpfNumber,

        @NotBlank
        String name,

        @NotBlank
        String phone,

        @NotBlank
        String email,

        @NotBlank
        String password
) {
}
