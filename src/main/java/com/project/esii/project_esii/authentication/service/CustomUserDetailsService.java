package com.project.esii.project_esii.authentication.service;

import com.project.esii.project_esii.authentication.utils.UserDetailsImpl;
import com.project.esii.project_esii.eventmanager.domain.entity.EventManager;
import com.project.esii.project_esii.eventmanager.service.EventManagerService;
import com.project.esii.project_esii.eventparticipant.domain.entity.EventParticipant;
import com.project.esii.project_esii.eventparticipant.service.EventParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final EventManagerService eventManagerService;
    private final EventParticipantService eventParticipantService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        EventManager eventManager = eventManagerService.findByEmail(email);
        if (eventManager != null) {
            return new UserDetailsImpl(eventManager);
        }

        EventParticipant eventParticipant = eventParticipantService.findByEmail(email);
        if (eventParticipant != null) {
            if(!eventParticipant.getIsEmailVerified()) {
                return null;
            }
            return new UserDetailsImpl(eventParticipant);
        }

        return null;
    }
}