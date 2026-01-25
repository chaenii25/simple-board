package com.example.simpleboard.dto.post;

import jakarta.validation.constraints.NotBlank;

public record PostUpdateRequest (
        @NotBlank String title,
        @NotBlank String content
) {}
