package com.ufpso.api.services.servImpl;

import com.ufpso.api.enums.Messages;
import com.ufpso.api.exception.AlreadyExists;
import com.ufpso.api.models.User;
import com.ufpso.api.repository.UserRepository;
import com.ufpso.api.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    public final UserRepository userRepository;
    public final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUser(User user) {
        Optional<User> userFindByEmail = userRepository.findByEmail(user.getEmail());
        if (userFindByEmail.isPresent()) {
            throw new AlreadyExists(Messages.USER_EMAIL_EXISTS.getMessage());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
