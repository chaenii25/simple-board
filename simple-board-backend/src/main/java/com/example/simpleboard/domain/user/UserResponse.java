package com.example.simpleboard.domain.user;

import java.time.LocalDateTime;

public record UserResponse (
        Long id,
        String email,
        LocalDateTime createdAt
) {}
