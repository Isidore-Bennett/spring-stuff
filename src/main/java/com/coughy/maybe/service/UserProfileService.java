package com.coughy.maybe.service;

import com.coughy.maybe.entity.UserProfile;
import com.coughy.maybe.repository.UserProfileRepository;
import com.coughy.maybe.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfile getByTitle(String title) {
        return userProfileRepository.findByTitle(title);
    }

}
