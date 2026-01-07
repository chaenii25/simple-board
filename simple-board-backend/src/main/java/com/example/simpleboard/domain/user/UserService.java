package com.example.simpleboard.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse registerUser(UserCreateRequest req) {
        if(userRepository.existsByEmail(req.email())) {
            throw new IllegalArgumentException("이미 사용중인 이메일입니다!");
        }

        User user = new User(req.email(), req.password());
        User saved =  userRepository.save(user);

        return new UserResponse(saved.getId(), saved.getEmail(), saved.getCreatedAt());
    }
}
