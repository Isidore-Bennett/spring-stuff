package com.coughy.maybe.service;

import com.coughy.maybe.entity.User;
import com.coughy.maybe.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private final UserRepository userRepository;
    private final UserProfileService userProfileService;

    public AccountService(UserRepository userRepository, UserProfileService userProfileService) {
        this.userRepository = userRepository;
        this.userProfileService = userProfileService;
    }

    public User registerUser(User user, String profile) {
        user.setUserProfile(userProfileService.getByTitle(profile));
        return userRepository.save(user);
    }

    public User verifyUser(User user) {
        Optional<User> returnedUser = userRepository.findByPassKeyAndLogin(user.getPassKey(), user.getLogin());
        return returnedUser.isPresent() ? returnedUser.get() : null;
    }

}
