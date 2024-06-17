package com.ufpso.api.services.servImpl;

import com.ufpso.api.enums.Messages;
import com.ufpso.api.exception.AlreadyExists;
import com.ufpso.api.models.User;
import com.ufpso.api.repository.UserRepository;
import com.ufpso.api.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    public final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(User user) {
        Optional<User> userFindByEmail = userRepository.findByEmail(user.getEmail());
        if (userFindByEmail.isPresent()) {
            throw new AlreadyExists(Messages.USER_EMAIL_EXISTS.getMessage());
        }
        userRepository.save(user);
    }
}
