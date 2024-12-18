package com.project.esii.project_esii.maineventtype.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MainEventTypeDetailsDTO(

        @NotNull
        Long id,

        @NotBlank
        String name
) {
}
