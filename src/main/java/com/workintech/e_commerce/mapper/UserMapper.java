package com.workintech.e_commerce.mapper;

import com.workintech.e_commerce.dto.UserPatchRequestDto;
import com.workintech.e_commerce.dto.UserRequestDto;
import com.workintech.e_commerce.dto.UserResponseDto;
import com.workintech.e_commerce.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequestDto userRequestDto){
        User user = new User();
        user.setFullName(userRequestDto.fullName());
        user.setEmail(userRequestDto.email());
        user.setPassword(userRequestDto.password());
        user.setRole(userRequestDto.role());
        return user;
    }

    public UserResponseDto toResponse(User user){
        return new UserResponseDto(user.getFullName(), user.getEmail(), user.getRole());
    }

    public void update(User userToUpdate, UserPatchRequestDto userPatchRequestDto){
        if (userPatchRequestDto.email() !=null){
            userToUpdate.setEmail(userPatchRequestDto.email());
        }
        if (userPatchRequestDto.fullName() != null){
            userToUpdate.setFullName(userPatchRequestDto.fullName());
        }
        if (userPatchRequestDto.role() != null){
            userToUpdate.setRole(userPatchRequestDto.role());
        }
        if ((userPatchRequestDto.password() != null)){
            userToUpdate.setPassword(userPatchRequestDto.password());
        }
    }
}
