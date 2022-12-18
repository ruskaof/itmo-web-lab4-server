package com.ruskaof.itmoweblab4server.service.user;

import com.ruskaof.itmoweblab4server.model.User;
import lombok.NonNull;

import java.util.Optional;

public interface UserService {
    Optional<User> getUser(@NonNull String username);

    /**
     * @return true if user was added, false if user already exists
     */
    boolean addUser(@NonNull User user);
}
