package com.project.esii.project_esii.exceptions.domain;

public class EmailNotVerifiedException extends RuntimeException {
    public EmailNotVerifiedException(){
        super("Não é possível realizar inscrições sem validar o e-mail. Por favor, verifique seu e-mail");
    }
}
