package com.project.esii.project_esii.user.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserFormDTO(

        @NotBlank
        String name,

        @NotBlank
        @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$")
        String cpf,

        @NotBlank
        String password,

        @NotBlank
        String email,

        @NotBlank
        String phone
) {
}
