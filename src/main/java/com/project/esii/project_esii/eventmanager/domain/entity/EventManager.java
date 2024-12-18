package com.project.esii.project_esii.eventmanager.domain.entity;

import com.project.esii.project_esii.authentication.domain.entity.BaseUser;
import com.project.esii.project_esii.naturalperson.domain.entity.NaturalPerson;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
public class EventManager extends NaturalPerson implements BaseUser {

    @Override
    public String getCpfNumber() {
        return super.getCpf().getCpfNumber();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }
}
