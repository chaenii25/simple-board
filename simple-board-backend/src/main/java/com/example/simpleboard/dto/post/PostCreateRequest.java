package com.example.simpleboard.dto.post;

import jakarta.validation.constraints.NotBlank;

public record PostCreateRequest (
        @NotBlank String title,
        @NotBlank String content
){
}
