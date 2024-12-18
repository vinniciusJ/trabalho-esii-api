package com.project.esii.project_esii.user.service;

import com.project.esii.project_esii.emailsender.service.EmailSenderService;
import com.project.esii.project_esii.excpetions.type.EntityNotFoundExcpetion;
import com.project.esii.project_esii.user.domain.dto.UserFormDTO;
import com.project.esii.project_esii.user.domain.entity.User;
import com.project.esii.project_esii.user.mapper.UserMapper;
import com.project.esii.project_esii.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EmailSenderService emailSenderService;

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

    public User create(UserFormDTO userFormDTO) {
        User newUser = UserMapper.convertUserFormDTOToEntity(userFormDTO);

        return userRepository.save(newUser);
    }

//    public boolean sendVerificationEmail(Long id, String email) {
//        return emailSenderService.sendEventVerificationEmail(email, id);
//    }


    public User getByIdOrNull(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);

    }

    public void setEmailToVerified(User user) {
        user.setIsEmailVerified(true);
        userRepository.save(user);
    }

    public void delete(Long id) {
        User user = findById(id);
        userRepository.delete(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExcpetion("User", "id", id.toString())
        );
    }
}
