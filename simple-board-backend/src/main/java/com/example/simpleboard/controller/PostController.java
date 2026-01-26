package com.example.simpleboard.controller;

import com.example.simpleboard.domain.post.Post;
import com.example.simpleboard.domain.post.PostRepository;
import com.example.simpleboard.domain.post.PostService;
import com.example.simpleboard.dto.post.PostCreateRequest;
import com.example.simpleboard.dto.post.PostResponse;
import com.example.simpleboard.dto.post.PostUpdateRequest;
import com.example.simpleboard.security.JwtPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;

    @PostMapping
    public PostResponse create(
            @Valid @RequestBody PostCreateRequest req,
            @AuthenticationPrincipal JwtPrincipal principal) {
        return postService.create(req, principal.userId());
    }

    @GetMapping
    public List<PostResponse> list() {
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public PostResponse detail(@PathVariable Long id){
        return postService.findById(id);
    }

    @PutMapping("/{id}")
    public PostResponse update(
            @PathVariable Long id,
            @Valid @RequestBody PostUpdateRequest req,
            @AuthenticationPrincipal JwtPrincipal principal
            ) {
        return postService.update(id, req, principal.userId());
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtPrincipal principal
            ) {
        postService.delete(id, principal.userId());
    }
}
