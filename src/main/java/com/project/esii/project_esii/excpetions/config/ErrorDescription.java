package com.project.esii.project_esii.excpetions.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDescription {
    private int statusCode;
    private String message;
}
