package com.coughy.maybe.service;

import com.coughy.maybe.entity.UserOrder;
import com.coughy.maybe.entity.UserOrderAddInKey;
import com.coughy.maybe.dto.UserDto;
import com.coughy.maybe.dto.UserOrderDto;
import com.coughy.maybe.repository.UserOrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class UserOrderService {

    private final UserOrderRepository userOrderRepository;
    private final UserOrderAddInService userOrderAddInService;

    public UserOrderService(UserOrderRepository userOrderRepository, UserOrderAddInService userOrderAddInService) {
        this.userOrderRepository = userOrderRepository;
        this.userOrderAddInService = userOrderAddInService;
    }


    public UserOrder saveUserOrder(UserOrder userOrder) {
        if (userOrder.getId() != null) {
            UserOrder dbUserOrder = userOrderRepository.findById(userOrder.getId()).get();
            userOrder.setOrderDate(dbUserOrder.getOrderDate());
        } else {
            userOrder.setOrderDate(new Date());
        }

        UserOrder savedUserOrder = userOrderRepository.save(userOrder);
        if (userOrder.getUserOrderAddIns() != null) {
            userOrder.getUserOrderAddIns().forEach(userOrderAddIn -> {
                userOrderAddIn.setId(new UserOrderAddInKey(userOrder.getId(), userOrderAddIn.getAddIn().getId()));
                userOrderAddIn.setUserOrder(userOrder);
            });
            userOrderAddInService.save(new ArrayList<>(userOrder.getUserOrderAddIns()));
        }

        userOrderAddInService.deleteAddInsNotPresent(userOrder);
        return savedUserOrder;
    }

    public ArrayList<UserOrder> findAll() {
        return new ArrayList<UserOrder>(userOrderRepository.findAll());
    }

    public ArrayList<UserOrder> getAllForBarista() {
        return new ArrayList<UserOrder>(userOrderRepository.findAllByOrderStatusIdNotContaining(-1L));
    }

    public ArrayList<UserOrder> getAllByUserId(String id) {
        return userOrderRepository.findAllByUserId(id);
    }

    public UserOrder getById(String id) {
        return userOrderRepository.findById(id).orElse(null);
    }

    public ArrayList<UserOrderDto> testUserOrderDto() {
        return userOrderRepository.testUserOrderDto();
    }

    public ArrayList<UserDto> testUserDto() {
        return userOrderRepository.testUserDto();
    }
}
