package com.example.simpleboard.dto.auth;

public record LoginResponse (
        Long userId,
        String email
) {}
