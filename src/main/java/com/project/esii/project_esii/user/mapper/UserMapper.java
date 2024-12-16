package com.project.esii.project_esii.user.mapper;

import com.project.esii.project_esii.user.domain.dto.PlainUserDTO;
import com.project.esii.project_esii.user.domain.entity.User;

public class UserMapper {
    public static PlainUserDTO convertEntityToPlainUserDTO(User user){
        return new PlainUserDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
