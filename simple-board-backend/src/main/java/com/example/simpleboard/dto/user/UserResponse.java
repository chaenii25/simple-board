package com.example.simpleboard.dto.user;

import java.time.LocalDateTime;

public record UserResponse (
        Long id,
        String email,
        LocalDateTime createdAt
) {}
