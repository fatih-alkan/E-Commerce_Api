package com.workintech.e_commerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workintech.e_commerce.entity.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserRequestDto(

        @JsonProperty("full_name")
        @NotNull
        @NotEmpty
        @NotBlank
        String fullName,

        @JsonProperty("email")
        @NotNull
        @NotEmpty
        @NotBlank
        String email,

        @JsonProperty("password")
        @NotNull
        @NotEmpty
        @NotBlank
        String password,

        @JsonProperty("role")
        Role role
) {
}
