package com.project.esii.project_esii.naturalperson.domain.entity;

import com.project.esii.project_esii.cpf.domain.entity.Cpf;
import com.project.esii.project_esii.person.domain.entity.Person;
import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public class NaturalPerson extends Person {

    @Embedded
    private Cpf cpf;
}
