package com.coughy.maybe.service;

import com.coughy.maybe.entity.AddIn;
import com.coughy.maybe.entity.User;
import com.coughy.maybe.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public User getById(String id) {
        return userRepository.getById(id);
    }

    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public Page<User> getAllByUserProfileTitle(String type, Pageable pageable) {
        return userRepository.getAllByUserProfileTitle(type, pageable);
    }

    public Boolean existsUserByLogin(String login) {
        return userRepository.existsUserByLogin(login);
    }

}
