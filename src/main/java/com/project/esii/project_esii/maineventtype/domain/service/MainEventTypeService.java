package com.project.esii.project_esii.maineventtype.domain.service;

import com.project.esii.project_esii.excpetions.type.EntityNotFoundExcpetion;
import com.project.esii.project_esii.maineventtype.domain.entity.MainEventType;
import com.project.esii.project_esii.maineventtype.domain.repository.MainEventTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainEventTypeService {

    private final MainEventTypeRepository mainEventTypeRepository;

    public MainEventType findById(Long id) {
        return mainEventTypeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExcpetion("MainEventType", "id", id.toString())
        );
    }
}
