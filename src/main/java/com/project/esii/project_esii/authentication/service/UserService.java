package com.project.esii.project_esii.authentication.service;

import com.project.esii.project_esii.authentication.domain.dto.LoginUserDto;
import com.project.esii.project_esii.authentication.domain.dto.RecoveryJwtTokenDto;
import com.project.esii.project_esii.authentication.utils.UserDetailsImpl;
import com.project.esii.project_esii.security.JwtTokenService;
import com.project.esii.project_esii.security.SecurityConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final SecurityConfiguration securityConfiguration;

//    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto) {
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());
//
//        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//
//        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
//    }
}
