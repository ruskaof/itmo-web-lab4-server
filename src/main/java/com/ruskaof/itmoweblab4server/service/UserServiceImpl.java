package com.ruskaof.itmoweblab4server.service;

import com.ruskaof.itmoweblab4server.model.User;
import com.ruskaof.itmoweblab4server.repository.UsersRepository;

import java.util.List;

public class UserServiceImpl implements UserService{
    private final UsersRepository usersRepository;
    // private final BCryptPasswordEncoder bCryptPasswordEncoder; TODO: add password encoding

    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public User register(User user) {
        return usersRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return usersRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    public User findById(Integer id) {
        return usersRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        usersRepository.deleteById(id);
    }
}
