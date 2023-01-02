package com.coughy.maybe.resource;

import com.coughy.maybe.entity.AddIn;
import com.coughy.maybe.entity.OrderStatus;
import com.coughy.maybe.service.OrderStatusService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.coughy.maybe.util.PageUtility.setPageRequest;

@RestController
@RequestMapping("/api/order-status")
public class OrderStatusResource {

    private final OrderStatusService orderStatusService;

    public OrderStatusResource(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @GetMapping()
    public ResponseEntity<ArrayList<OrderStatus>> getOrderStatuses() {
        return new ResponseEntity<>(orderStatusService.findAll(), HttpStatus.OK);
    }
}
