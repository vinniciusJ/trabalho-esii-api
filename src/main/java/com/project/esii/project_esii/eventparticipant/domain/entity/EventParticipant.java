package com.project.esii.project_esii.eventparticipant.domain.entity;

import com.project.esii.project_esii.authentication.domain.entity.BaseUser;
import com.project.esii.project_esii.mainevent.domain.entity.MainEvent;
import com.project.esii.project_esii.maineventaction.domain.entity.MainEventAction;
import com.project.esii.project_esii.naturalperson.domain.entity.NaturalPerson;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class EventParticipant extends NaturalPerson implements BaseUser {

    @ManyToMany(mappedBy = "eventParticipants")
    private Set<MainEvent> mainEvents;

    @ManyToMany(mappedBy = "eventParticipants")
    private Set<MainEventAction> mainEventActions;

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
