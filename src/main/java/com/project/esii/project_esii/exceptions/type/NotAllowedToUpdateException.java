package com.project.esii.project_esii.excpetions.type;

import lombok.Getter;

@Getter
public class NotAllowedToUpdateException extends RuntimeException {

    private String entity;
    private String field;
    private String value;

    public NotAllowedToUpdateException(String entity, String field, String value) {
        super();
        this.entity = entity;
        this.field = field;
        this.value = value;
    }
}
