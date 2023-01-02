package com.coughy.maybe.repository;

import com.coughy.maybe.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {

    UserProfile findByTitle (String title);
}
