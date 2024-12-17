package com.project.esii.project_esii.cpf.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Embeddable
public class Cpf {

    @NotNull
    @Column(nullable = false, unique = true)
    private String cpfNumber;
}
