package com.project.esii.project_esii.exceptions.type;

import lombok.Data;

@Data
public class NoVacancyForMainEventActionException extends RuntimeException {

    private String value;

    public NoVacancyForMainEventActionException(String value) {
        super();
        this.value = value;
    }
}
