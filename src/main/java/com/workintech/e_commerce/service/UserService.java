package com.workintech.e_commerce.service;

import com.workintech.e_commerce.dto.UserPatchRequestDto;
import com.workintech.e_commerce.dto.UserRequestDto;
import com.workintech.e_commerce.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAll();
    UserResponseDto getById(Long id);
    UserResponseDto create(UserRequestDto userRequestDto);
    UserResponseDto replaceOrCreate(Long id, UserRequestDto userRequestDto);
    UserResponseDto update(Long id, UserPatchRequestDto userPatchRequestDto);
    void delete(Long id);
}
