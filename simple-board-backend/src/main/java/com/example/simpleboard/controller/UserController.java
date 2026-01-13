package com.example.simpleboard.controller;

import com.example.simpleboard.dto.user.UserCreateRequest;
import com.example.simpleboard.dto.user.UserResponse;
import com.example.simpleboard.domain.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody UserCreateRequest req) {
        return userService.register(req);
    }
}
