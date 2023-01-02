package com.coughy.maybe.repository;

import com.coughy.maybe.entity.AddIn;
import com.coughy.maybe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddInRepository extends JpaRepository<AddIn, String> {
    List<AddIn> getAllByVisibleTrue();
}
