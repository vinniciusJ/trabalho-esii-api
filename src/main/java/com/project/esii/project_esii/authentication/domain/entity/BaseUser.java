package com.project.esii.project_esii.authentication.domain.entity;

import com.project.esii.project_esii.enums.PersonRole;

public interface BaseUser {
    String getCpfNumber();
    String getName();
    String getEmail();
    String getPassword();
    PersonRole getPersonRole();
}
