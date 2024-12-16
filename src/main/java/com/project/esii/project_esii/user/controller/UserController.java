package com.project.esii.project_esii.user.controller;

import com.project.esii.project_esii.user.domain.dto.PlainUserDTO;
import com.project.esii.project_esii.user.domain.dto.UserFormDTO;
import com.project.esii.project_esii.user.domain.entity.User;
import com.project.esii.project_esii.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<PlainUserDTO> create(@RequestBody UserFormDTO userFormDTO) {
        PlainUserDTO plainUserDTO = userService.create(userFormDTO);
        userService.sendVerificationEmail(plainUserDTO.id(), plainUserDTO.email());

        return ResponseEntity.status(HttpStatus.CREATED).body(plainUserDTO);
    }

    @PostMapping("/verify-email/{id}")
    public ResponseEntity<String> verifyEmail(@PathVariable Long id) {
        User user = userService.getByIdOrNull(id);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuário não encontrado");
        }

        userService.setEmailToVerified(user);

        return ResponseEntity.ok("E-mail verificado com sucesso!");
    }
}
