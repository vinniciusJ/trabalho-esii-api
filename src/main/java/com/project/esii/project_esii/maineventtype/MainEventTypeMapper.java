package com.project.esii.project_esii.maineventtype;

import com.project.esii.project_esii.maineventtype.domain.dto.MainEventTypeDetailsDTO;
import com.project.esii.project_esii.maineventtype.domain.dto.MainEventTypeFormDTO;
import com.project.esii.project_esii.maineventtype.domain.entity.MainEventType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MainEventTypeMapper {
    MainEventType convertMainEventTypeFormDTOToMainEventType(MainEventTypeFormDTO mainEventTypeFormDTO);

    MainEventTypeDetailsDTO convertMainEventTypeToMainEventTypeDetailsDTO(MainEventType mainEventType);
}
