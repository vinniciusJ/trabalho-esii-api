package com.project.esii.project_esii.user.controller;

import com.project.esii.project_esii.user.domain.dto.UserDetailsDTO;
import com.project.esii.project_esii.user.domain.dto.UserFormDTO;
import com.project.esii.project_esii.user.domain.entity.User;
import com.project.esii.project_esii.user.mapper.UserMapper;
import com.project.esii.project_esii.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

//    @PostMapping
//    public ResponseEntity<UserDetailsDTO> create(@RequestBody UserFormDTO userFormDTO) {
//        User user = userService.create(userFormDTO);
//
//        boolean emailSent = userService.sendVerificationEmail(user.getId(), user.getEmail());
//        if (!emailSent) {
//            userService.delete(user.getId());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.convertEntityToUserDetailsDTO(user));
//    }

    @PostMapping("/verify-email/{id}")
    public ResponseEntity<String> verifyEmail(@PathVariable Long id) {
        User user = userService.findById(id);
        userService.setEmailToVerified(user);

        return ResponseEntity.ok("E-mail verificado com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsDTO> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.convertEntityToUserDetailsDTO(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
