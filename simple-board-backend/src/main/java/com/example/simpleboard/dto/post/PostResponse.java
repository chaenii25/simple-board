package com.example.simpleboard.dto.post;

import java.time.LocalDateTime;

public record PostResponse (
        Long id,
        String title,
        String content,
        Long authorId,
        LocalDateTime createdAt
) {
}
