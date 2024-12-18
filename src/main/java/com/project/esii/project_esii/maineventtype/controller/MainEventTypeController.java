package com.project.esii.project_esii.maineventtype.controller;

import com.project.esii.project_esii.maineventtype.domain.dto.MainEventTypeDetailsDTO;
import com.project.esii.project_esii.maineventtype.domain.dto.MainEventTypeFormDTO;
import com.project.esii.project_esii.maineventtype.domain.entity.MainEventType;
import com.project.esii.project_esii.maineventtype.service.MainEventTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/main-event-type")
@RequiredArgsConstructor
public class MainEventTypeController {

    private final MainEventTypeService mainEventTypeService;

    @PostMapping
    public ResponseEntity<MainEventTypeDetailsDTO> create(@RequestBody MainEventTypeFormDTO mainEventTypeFormDTO) {
        MainEventType mainEventType = mainEventTypeService.save(mainEventTypeFormDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(mainEventTypeService.convertMainEventTypeToMainEventTypeDetailsDTO(mainEventType));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MainEventTypeDetailsDTO>> listAll() {
        List<MainEventType> mainEventTypeList = mainEventTypeService.findAll();

        return ResponseEntity.status(HttpStatus.CREATED).body(mainEventTypeService.convertToMainEventTypeDetailsDTOList(mainEventTypeList));
    }

    @GetMapping
    public ResponseEntity<Page<MainEventTypeDetailsDTO>> list(Pageable pageable) {
        Page<MainEventType> mainEventTypePage = mainEventTypeService.findAll(pageable);

        return ResponseEntity.status(HttpStatus.CREATED).body(mainEventTypeService.convertToMainEventTypeDetailsDTOPage(mainEventTypePage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MainEventTypeDetailsDTO> findById(@PathVariable Long id) {
        MainEventType mainEventType = mainEventTypeService.findById(id);

        return ResponseEntity.ok(mainEventTypeService.convertMainEventTypeToMainEventTypeDetailsDTO(mainEventType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        MainEventType mainEventType = mainEventTypeService.findById(id);
        mainEventTypeService.delete(mainEventType);

        return ResponseEntity.noContent().build();
    }
}
