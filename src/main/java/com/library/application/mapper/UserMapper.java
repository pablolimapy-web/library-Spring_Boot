package com.library.application.mapper;

import com.library.domain.model.User;
import com.library.application.dto.UserDTO;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        if (user == null) return null;
        return new UserDTO(user.getId(), user.getName(), user.getEmail());
    }

    public static User toEntity(UserDTO dto) {
        if (dto == null) return null;
        return new User(dto.getId(), dto.getName(), dto.getEmail());
    }
}
