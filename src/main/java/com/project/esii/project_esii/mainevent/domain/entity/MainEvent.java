package com.project.esii.project_esii.mainevent.domain.entity;

import com.project.esii.project_esii.activity.domain.entity.Activity;
import com.project.esii.project_esii.maineventtype.domain.entity.MainEventType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "main_event")
@EqualsAndHashCode(callSuper = true)
public class MainEvent extends Activity {

    @ManyToOne
    @JoinColumn(name = "id")
    private MainEventType mainEventType;
}
