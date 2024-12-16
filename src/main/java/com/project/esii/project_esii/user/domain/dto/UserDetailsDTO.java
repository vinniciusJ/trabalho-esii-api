package com.project.esii.project_esii.user.domain.dto;

public record UserDetailsDTO(

        Long id,

        String name,

        String cpf,

        String email,

        String phone
) {
}
