package com.project.esii.project_esii.user.service;

import com.project.esii.project_esii.user.domain.entity.User;
import com.project.esii.project_esii.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private void verifyUserEmail(Long userId){
        verifyUserExistsById(userId);
        userRepository.verifyUserEmail(userId);
    }

    private User getOrElseThrow(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + userId));
    }

    private void verifyUserExistsById(Long userId){
        if(!userRepository.existsById(userId)){
            throw new RuntimeException("Usuário não encontrado com ID: " + userId);
        }
    }
}
