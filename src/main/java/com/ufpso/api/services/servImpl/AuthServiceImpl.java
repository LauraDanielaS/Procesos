package com.ufpso.api.services.servImpl;

import com.ufpso.api.config.JWTService;
import com.ufpso.api.dtos.AuthRequest;
import com.ufpso.api.dtos.AuthResponse;
import com.ufpso.api.enums.Messages;
import com.ufpso.api.exception.AuthenticationFailedException;
import com.ufpso.api.models.User;
import com.ufpso.api.repository.UserRepository;
import com.ufpso.api.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequest.getEmail(), authRequest.getPassword()
            ));
        } catch (Exception e) {
            throw new AuthenticationFailedException(Messages.CREDENTIAL_INVALID.getMessage());
        }
        Optional<User> user = userRepository.findByEmail(authRequest.getEmail());
        if (user.isEmpty()) {
            throw new AuthenticationFailedException(Messages.CREDENTIAL_INVALID.getMessage());
        }
        UserDetails userDetails = user.get();
        String token = jwtService.generateToken(userDetails);
        return AuthResponse.builder()
                .token(token)
                .build();
    }
}