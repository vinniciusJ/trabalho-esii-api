package com.project.esii.project_esii.user.mapper;

import com.project.esii.project_esii.user.domain.dto.PlainUserDTO;
import com.project.esii.project_esii.user.domain.dto.UserFormDTO;
import com.project.esii.project_esii.user.domain.entity.User;

public class UserMapper {
    public static PlainUserDTO convertEntityToPlainUserDTO(User user){
        return new PlainUserDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public static User convertUserFormDTOToEntity(UserFormDTO userFormDTO){
        User user = new User();
        user.setName(userFormDTO.name());
        user.setCpf(userFormDTO.cpf());
        user.setPassword(userFormDTO.password());
        user.setEmail(userFormDTO.email());
        user.setPhone(userFormDTO.phone());

        return user;
    }
}
