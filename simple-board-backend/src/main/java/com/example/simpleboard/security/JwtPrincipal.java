package com.example.simpleboard.security;

public record JwtPrincipal (Long userId, String email) {
}
