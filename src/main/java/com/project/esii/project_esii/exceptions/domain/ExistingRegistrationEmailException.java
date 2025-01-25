package com.project.esii.project_esii.exceptions.domain;

import lombok.Getter;

@Getter
public class ExistingRegistrationEmailException extends RuntimeException {

    private String userType;
    private String email;

    public ExistingRegistrationEmailException(String userType, String email) {
        super();
        this.userType = userType;
        this.email = email;
    }
}
