package com.example.simpleboard.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

//DTO
public record UserCreateRequest (
        @Email @NotBlank String email,
        @NotBlank String password
) {}
