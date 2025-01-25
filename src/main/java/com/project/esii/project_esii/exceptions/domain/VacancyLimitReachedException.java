package com.project.esii.project_esii.exceptions.domain;

public class VacancyLimitReachedException extends RuntimeException{
    public VacancyLimitReachedException(Integer limit) {
        super("Limites de vagas atingido: " + limit + "/" + limit);
    }
}
