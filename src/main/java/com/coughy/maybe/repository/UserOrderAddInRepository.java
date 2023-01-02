package com.coughy.maybe.repository;

import com.coughy.maybe.entity.AddIn;
import com.coughy.maybe.entity.UserOrder;
import com.coughy.maybe.entity.UserOrderAddIn;
import com.coughy.maybe.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Set;

@Repository
public interface UserOrderAddInRepository extends JpaRepository<UserOrderAddIn, String> {

    Set<UserOrderAddIn> findByUserOrder (UserOrder userOrder);

    @Modifying
    void deleteAllByAddInIn(Set<AddIn> addIns);
}
