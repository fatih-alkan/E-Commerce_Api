package com.workintech.e_commerce.dto;

import com.workintech.e_commerce.entity.Role;

public record UserResponseDto(
        String fullName,
        String email,
        Role role
) {
}
