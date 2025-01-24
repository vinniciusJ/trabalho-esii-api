package com.project.esii.project_esii.authentication.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginUserDto(

        @NotBlank
        String email,

        @NotBlank
        String password

) {
}
