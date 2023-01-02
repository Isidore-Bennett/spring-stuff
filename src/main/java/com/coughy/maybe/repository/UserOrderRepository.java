package com.coughy.maybe.repository;

import com.coughy.maybe.entity.UserOrder;
import com.coughy.maybe.dto.UserDto;
import com.coughy.maybe.dto.UserOrderDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, String> {

    ArrayList<UserOrder> findAllByUserId(String id);

    ArrayList<UserOrder> findAllByOrderStatusIdNotContaining(Long id);

    @Query("select uo.id from UserOrder uo where uo.id = '9a11d5ea-7213-451e-b08d-fd284fad9526'")
    String asd();

    @Query("SELECT new com.coughy.maybe.dto.UserOrderDto(uo.user.id, uo.user.firstName, uo.user.lastName, uo.user.userProfile) FROM UserOrder uo")
    ArrayList<UserOrderDto> testUserOrderDto();

//    @Query("SELECT new com.coughy.maybe.dto.UserDto(u.id, p, u.userProfile) " +
//            "FROM UserOrder uo " +
//            "INNER JOIN User u ON uo.user.id = u.id " +
//            "INNER JOIN Product p ON uo.product.id = p.id")
    @Query("SELECT new com.coughy.maybe.dto.UserDto(u) " +
            "FROM User u")
    ArrayList<UserDto> testUserDto();
}
