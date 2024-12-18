package com.project.esii.project_esii.eventparticipant.domain.dto;

import com.project.esii.project_esii.enums.PersonRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
