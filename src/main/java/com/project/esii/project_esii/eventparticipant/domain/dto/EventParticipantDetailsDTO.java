package com.project.esii.project_esii.eventparticipant.domain.dto;

import com.project.esii.project_esii.enums.PersonRole;
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

        @NotNull
        boolean isEmailVerified,

        @NotNull
        PersonRole personRole
) {
}
