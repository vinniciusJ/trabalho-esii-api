package com.project.esii.project_esii.authentication.controller;

import com.project.esii.project_esii.authentication.domain.dto.LoginUserDto;
import com.project.esii.project_esii.authentication.domain.dto.RecoveryJwtTokenDto;
import com.project.esii.project_esii.authentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(@RequestBody LoginUserDto loginUserDto) {
        RecoveryJwtTokenDto recoveryJwtTokenDto = authenticationService.authenticate(loginUserDto.email(), loginUserDto.password());
        return new ResponseEntity<>(recoveryJwtTokenDto, HttpStatus.OK);
    }
}