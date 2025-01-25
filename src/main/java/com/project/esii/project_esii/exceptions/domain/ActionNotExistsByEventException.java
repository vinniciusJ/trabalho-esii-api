package com.project.esii.project_esii.exceptions.domain;

public class ActionNotExistsByEventException extends RuntimeException{
    public ActionNotExistsByEventException(String action, String event) {
        super("A ação " + action + " não existe para o evento " + event);
    }
}
