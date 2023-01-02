package com.coughy.maybe.service;

import com.coughy.maybe.entity.OrderStatus;
import com.coughy.maybe.entity.UserOrder;
import com.coughy.maybe.repository.OrderStatusRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderStatusService {

    private final OrderStatusRepository orderStatusRepository;

    public OrderStatusService(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    public ArrayList<OrderStatus> findAll() {
        return new ArrayList<OrderStatus>(orderStatusRepository.findAll());
    }
}
