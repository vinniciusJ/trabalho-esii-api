package com.project.esii.project_esii.authentication.service;

import com.project.esii.project_esii.authentication.domain.entity.BaseUser;
import com.project.esii.project_esii.eventmanager.service.EventManagerService;
import com.project.esii.project_esii.eventparticipant.service.EventParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final EventManagerService eventManagerService;
    private final EventParticipantService eventParticipantService;

    public BaseUser authenticate(String email, String password) {
        BaseUser user = eventManagerService.findByEmail(email);
        if (user == null) {
            user = eventParticipantService.findByEmail(email);
        }

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        throw new UsernameNotFoundException("User not found or password incorrect");
    }
}
