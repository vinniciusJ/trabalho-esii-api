package com.project.esii.project_esii.eventparticipant.domain.entity;

import com.project.esii.project_esii.authentication.domain.entity.BaseUser;
import com.project.esii.project_esii.mainevent.domain.entity.MainEvent;
import com.project.esii.project_esii.maineventaction.domain.entity.MainEventAction;
import com.project.esii.project_esii.naturalperson.domain.entity.NaturalPerson;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class EventParticipant extends NaturalPerson implements BaseUser {

//    @NotNull
//    @ManyToOne
//    @JoinColumn(name = "main_event_id", nullable = false)
//    private MainEvent mainEvent;  // Relacionamento com MainEvent
//
//    @ManyToOne
//    @JoinColumn(name = "main_event_action_id", nullable = false)
//    private MainEventAction mainEventAction;  // Relacionamento com MainEventAction

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
