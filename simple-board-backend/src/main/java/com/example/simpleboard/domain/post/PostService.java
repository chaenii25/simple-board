package com.example.simpleboard.domain.post;

import com.example.simpleboard.domain.user.User;
import com.example.simpleboard.domain.user.UserRepository;
import com.example.simpleboard.dto.post.PostCreateRequest;
import com.example.simpleboard.dto.post.PostResponse;
import com.example.simpleboard.dto.post.PostUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional(readOnly = true)
    public List<PostResponse> findAll() {
        return postRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(post -> new PostResponse(
                        post.getId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getAuthor().getId(),
                        post.getCreatedAt()
                ))
                .toList();
    }

    @Transactional(readOnly = true)
    public PostResponse findById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor().getId(),
                post.getCreatedAt()
        );
    }

    @Transactional
    public PostResponse update(Long postId, PostUpdateRequest req, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

        if (!post.getAuthor().getId().equals(userId)) {
            throw new IllegalStateException("작성자 아님");
        }

        post.update(req.title(), req.content());

        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor().getId(),
                post.getCreatedAt()
        );
    }

    @Transactional
    public void delete(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

        if(!post.getAuthor().getId().equals(userId)) {
            throw new IllegalStateException("작성자 아님");
        }

        postRepository.delete(post);
    }
}
