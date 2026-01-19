package com.example.simpleboard.domain.user;

import com.example.simpleboard.dto.auth.LoginRequest;
import com.example.simpleboard.dto.auth.LoginResponse;
import com.example.simpleboard.dto.user.UserCreateRequest;
import com.example.simpleboard.dto.user.UserResponse;
import com.example.simpleboard.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public UserResponse register(UserCreateRequest req) {
        if(userRepository.existsByEmail(req.email())) {
            throw new EmailAlreadyExistsException("이미 사용중인 이메일입니다.");
        }

        String encoded = passwordEncoder.encode(req.password());
        User user = new User(req.email(), encoded);
        User saved =  userRepository.save(user);

        return new UserResponse(saved.getId(), saved.getEmail(), saved.getCreatedAt());
    }

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest req) {
        User user = userRepository.findByEmail(req.email())
                .orElseThrow(() -> new UserNotFoundException("존재하지않는 이메일입니다."));

        if (!passwordEncoder.matches(req.password(), user.getPassword())) {
            throw new InvalidPasswordException("비밀번호가 올바르지 않습니다.");
        }

        String token = jwtProvider.createAccessToken(user.getId(),  user.getEmail());
        return new LoginResponse(token);
    }
}
