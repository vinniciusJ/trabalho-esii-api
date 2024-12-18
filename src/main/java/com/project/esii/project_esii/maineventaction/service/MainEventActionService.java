package com.project.esii.project_esii.maineventaction.service;

import com.project.esii.project_esii.eventmanager.domain.entity.EventManager;
import com.project.esii.project_esii.excpetions.type.EntityNotFoundExcpetion;
import com.project.esii.project_esii.mainevent.domain.entity.MainEvent;
import com.project.esii.project_esii.maineventaction.domain.dto.MainEventActionDetailsDTO;
import com.project.esii.project_esii.maineventaction.domain.dto.MainEventActionFormDTO;
import com.project.esii.project_esii.maineventaction.domain.entity.MainEventAction;
import com.project.esii.project_esii.maineventaction.mapper.MainEventActionMapper;
import com.project.esii.project_esii.maineventaction.repository.MainEventActionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainEventActionService {

    private final MainEventActionRepository mainEventActionRepository;
    private final MainEventActionMapper mainEventActionMapper;

    public MainEventAction save(MainEventActionFormDTO mainEventActionFormDTO, MainEvent mainEvent, EventManager eventManager) {
        MainEventAction mainEventAction = convertMainEventActionFormDTOToMainEventAction(mainEventActionFormDTO, mainEvent, eventManager);

        return mainEventActionRepository.save(mainEventAction);
    }

    private MainEventAction convertMainEventActionFormDTOToMainEventAction(MainEventActionFormDTO mainEventActionFormDTO, MainEvent mainEvent, EventManager eventManager) {
        return mainEventActionMapper.convertMainEventActionFormDTOToMainEventAction(mainEventActionFormDTO, mainEvent, eventManager);
    }

    public MainEventActionDetailsDTO convertMainEventActionToMainEventActionDetailsDTO(MainEventAction mainEventAction) {
        return mainEventActionMapper.convertMainEventActionToMainEventActionDetailsDTO(mainEventAction);
    }

    public Page<MainEventAction> findAll(Pageable pageable) {
        return mainEventActionRepository.findAll(pageable);
    }

    public Page<MainEventActionDetailsDTO> convertToMainEventActionDetailsDTOPage(Page<MainEventAction> mainEventActionPage) {
        return mainEventActionPage.map(this::convertMainEventActionToMainEventActionDetailsDTO);
    }

    public MainEventAction findById(Long id) {
        return mainEventActionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExcpetion("MainEventAction", "id", id.toString())
        );
    }

    public void delete(MainEventAction mainEventAction) {
        mainEventActionRepository.delete(mainEventAction);
    }
}
