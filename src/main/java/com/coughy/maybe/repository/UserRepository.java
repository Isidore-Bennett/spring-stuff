package com.coughy.maybe.repository;

import com.coughy.maybe.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByLogin(String login);

    Optional<User> findByPassKeyAndLogin(String passKey, String login);

    Boolean existsUserByLogin(String login);

    Page<User> getAllByUserProfileTitle(String type, Pageable pageable);
}
