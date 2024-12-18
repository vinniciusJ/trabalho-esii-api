package com.project.esii.project_esii.authentication.service;

import com.project.esii.project_esii.authentication.domain.dto.RecoveryJwtTokenDto;
import com.project.esii.project_esii.authentication.utils.UserDetailsImpl;
import com.project.esii.project_esii.security.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;



//    public BaseUser authenticate(String email, String password) {
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                new UsernamePasswordAuthenticationToken(email, password);
//
//        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//
////        BaseUser user = eventManagerService.findByEmail(email);
////        if (user == null) {
////            user = eventParticipantService.findByEmail(email);
////        }
////
////        if (user != null && user.getPassword().equals(password)) {
////            return user;
////        }
////
////        throw new UsernameNotFoundException("User not found or password incorrect");
//        return userDetails;
//    }

    public RecoveryJwtTokenDto authenticate(String email, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }
}
