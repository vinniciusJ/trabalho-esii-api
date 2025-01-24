package com.project.esii.project_esii.eventsubscription.mapper;

import com.project.esii.project_esii.eventsubscription.domain.dto.EventSubscriptionDetailsDTO;
import com.project.esii.project_esii.eventsubscription.domain.entity.EventSubscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventSubscriptionMapper {

    @Mapping(source = "eventSubscription.mainEvent", target = "mainEventDTO")
    @Mapping(source = "eventSubscription.eventParticipant", target = "eventParticipantDetailsDTO")
    @Mapping(source = "eventSubscription.eventParticipant.cpf.cpfNumber", target = "EventParticipantDetailsDTO.cpfNumber")
    @Mapping(source = "EventSubscription.mainEventActionList", target = "mainEventActionDetailsDTOList")
    EventSubscriptionDetailsDTO convertEventSubscriptionToEventSubscriptionDetailsDTO(EventSubscription eventSubscription);
}
