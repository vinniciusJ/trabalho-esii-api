package com.project.esii.project_esii.maineventtype.service;

import com.project.esii.project_esii.excpetions.type.EntityNotFoundExcpetion;
import com.project.esii.project_esii.maineventtype.MainEventTypeMapper;
import com.project.esii.project_esii.maineventtype.domain.dto.MainEventTypeDetailsDTO;
import com.project.esii.project_esii.maineventtype.domain.dto.MainEventTypeFormDTO;
import com.project.esii.project_esii.maineventtype.domain.entity.MainEventType;
import com.project.esii.project_esii.maineventtype.repository.MainEventTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainEventTypeService {

    private final MainEventTypeRepository mainEventTypeRepository;
    private final MainEventTypeMapper mainEventTypeMapper;

    public MainEventType findById(Long id) {
        return mainEventTypeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExcpetion("MainEventType", "id", id.toString())
        );
    }

    public MainEventType save(MainEventTypeFormDTO mainEventTypeFormDTO) {
        MainEventType mainEventType = convertMainEventTypeFormDTOToMainEventType(mainEventTypeFormDTO);

        return mainEventTypeRepository.save(mainEventType);
    }

    private MainEventType convertMainEventTypeFormDTOToMainEventType(MainEventTypeFormDTO mainEventTypeFormDTO) {
        return mainEventTypeMapper.convertMainEventTypeFormDTOToMainEventType(mainEventTypeFormDTO);
    }

    public MainEventTypeDetailsDTO convertMainEventTypeToMainEventTypeDetailsDTO(MainEventType mainEventType) {
        return mainEventTypeMapper.convertMainEventTypeToMainEventTypeDetailsDTO(mainEventType);
    }

    public Page<MainEventType> findAll(Pageable pageable) {
        return mainEventTypeRepository.findAll(pageable);
    }

    public Page<MainEventTypeDetailsDTO> convertToMainEventTypeDetailsDTOPage(Page<MainEventType> mainEventTypePage) {
        return mainEventTypePage.map(mainEventTypeMapper::convertMainEventTypeToMainEventTypeDetailsDTO);
    }

    public void delete(MainEventType mainEventType) {
        mainEventTypeRepository.delete(mainEventType);
    }
}
