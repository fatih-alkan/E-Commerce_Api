package com.workintech.e_commerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workintech.e_commerce.entity.Role;

public record UserPatchRequestDto(
        @JsonProperty("full_name")
        String fullName,
        String email,
        String password,
        Role role
) {
}
