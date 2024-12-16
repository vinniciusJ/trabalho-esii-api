package com.project.esii.project_esii.user.controller;

import com.project.esii.project_esii.user.domain.dto.PlainUserDTO;
import com.project.esii.project_esii.user.domain.dto.UserFormDTO;
import com.project.esii.project_esii.user.domain.entity.User;
import com.project.esii.project_esii.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<PlainUserDTO> create(@RequestBody UserFormDTO userFormDTO) {
        PlainUserDTO plainUserDTO = userService.create(userFormDTO);

        boolean emailSent = userService.sendVerificationEmail(plainUserDTO.id(), plainUserDTO.email());

        if (!emailSent) {
            userService.delete(plainUserDTO.id());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(plainUserDTO);
    }

    @PostMapping("/verify-email/{id}")
    public void verifyEmail(@PathVariable Long id, HttpServletResponse response) throws IOException {
        User user = userService.getByIdOrNull(id);
        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Usuário não encontrado");
            return;
        }

        userService.setEmailToVerified(user);

        response.sendRedirect("LINK FRONT");
    }
}
