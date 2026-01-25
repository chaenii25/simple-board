package com.example.simpleboard.domain.post;

import com.example.simpleboard.domain.user.User;
import com.example.simpleboard.domain.user.UserRepository;
import com.example.simpleboard.dto.post.PostCreateRequest;
import com.example.simpleboard.dto.post.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostResponse create(PostCreateRequest req, Long userId) {
        User user  = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        Post post = new Post(req.title(), req.content(), user);
        Post saved = postRepository.save(post);

        return new PostResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getContent(),
                user.getId(),
                saved.getCreatedAt()
        );
    }
}
