package com.ruskaof.itmoweblab4server.service.user;

import com.ruskaof.itmoweblab4server.model.User;
import com.ruskaof.itmoweblab4server.repository.UsersRepository;
import com.ruskaof.itmoweblab4server.security.UserPasswordUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;

    @Override
    public Optional<User> getUser(@NonNull String username) {
        final var user = usersRepository.findByUsername(username);
        return Optional.ofNullable(user);
    }

    @Override
    public boolean addUser(@NonNull User user) {
        if (usersRepository.findByUsername(user.getUsername()) != null) {
            return false;
        }
        user.setPassword(UserPasswordUtil.hashPassword(user.getPassword()));
        usersRepository.save(user);
        return true;
    }
}
