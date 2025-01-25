package com.project.esii.project_esii.eventsubscription.mapper;

import com.project.esii.project_esii.eventsubscription.domain.dto.EventSubscriptionDetailsDTO;
import com.project.esii.project_esii.eventsubscription.domain.entity.EventSubscription;
import com.project.esii.project_esii.maineventaction.domain.dto.MainEventActionDetailsDTO;
import com.project.esii.project_esii.maineventaction.domain.entity.MainEventAction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface EventSubscriptionMapper {

    @Mapping(source = "eventSubscription.mainEvent", target = "mainEventDTO")
    @Mapping(source = "eventSubscription.eventParticipant", target = "eventParticipantDetailsDTO")
    @Mapping(source = "eventSubscription.eventParticipant.cpf.cpfNumber", target = "eventParticipantDetailsDTO.cpfNumber")
    @Mapping(source = "eventSubscription.mainEventActionList", target = "mainEventActionDetailsDTOList")
    @Mapping(source = "eventSubscription.mainEvent.eventManager", target = "mainEventDTO.eventManagerDetailsDTO")
    @Mapping(source = "eventSubscription.mainEvent.eventManager.cpf.cpfNumber", target = "mainEventDTO.eventManagerDetailsDTO.cpfNumber")
    @Mapping(source = "eventSubscription.mainEvent.mainEventType", target = "mainEventDTO.mainEventTypeDetailsDTO")
    EventSubscriptionDetailsDTO convertEventSubscriptionToEventSubscriptionDetailsDTO(EventSubscription eventSubscription);
}
