package com.coughy.maybe.resource;

import com.coughy.maybe.entity.UserOrder;
import com.coughy.maybe.dto.UserDto;
import com.coughy.maybe.dto.UserOrderDto;
import com.coughy.maybe.service.UserOrderService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/user-order")
public class UserOrderResource {

    private final UserOrderService userOrderService;

    public UserOrderResource(UserOrderService userOrderService) {
        this.userOrderService = userOrderService;
    }

    @PostMapping("")
    public ResponseEntity<UserOrder> createUserOrder(@RequestBody UserOrder userOrder) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(userOrderService.saveUserOrder(userOrder), headers, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<UserOrder> updateUserOrder(@RequestBody UserOrder userOrder) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(userOrderService.saveUserOrder(userOrder), headers, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<ArrayList<UserOrder>> getAll() {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(userOrderService.findAll(), headers, HttpStatus.OK);
    }

    @GetMapping("/barista")
    public ResponseEntity<ArrayList<UserOrder>> getAllForBarista() {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(userOrderService.getAllForBarista(), headers, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ArrayList<UserOrder>> getAllByUserId(@PathVariable String userId) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(userOrderService.getAllByUserId(userId), headers, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserOrder> getById(@PathVariable String id) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(userOrderService.getById(id), headers, HttpStatus.OK);
    }

    @GetMapping("/test1")
    @RolesAllowed("ROLE_USER")
    public ResponseEntity<ArrayList<UserOrderDto>> testUserOrderDto() {
        return new ResponseEntity<>(userOrderService.testUserOrderDto(), HttpStatus.OK);
    }

    @GetMapping("/test2")
    @RolesAllowed({"USER", "BARISTA"})
    public ResponseEntity<ArrayList<UserDto>> testUserDto() {
        return new ResponseEntity<>(userOrderService.testUserDto(), HttpStatus.OK);
    }

    @GetMapping("/test3")
    public ResponseEntity<ArrayList<UserDto>> testEndpoint() {
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("first");

        testMethod(arrayList);


        return new ResponseEntity<>(new ArrayList<>() ,HttpStatus.OK);
    }

    @GetMapping("/test4")
    public ResponseEntity<Boolean> testEndpoint4() {
        String a = "A";
        String b = "A";

        Boolean zxc = (Boolean) null;

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/test5/{id}")
    public ResponseEntity<String> testEndpoint5(@PathVariable String id) {
        if (id.equals("1")) {
            return new ResponseEntity<>(" has been balanced successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(" has been balanced and taken over successfully", HttpStatus.OK);
    }

    private ArrayList<String> testMethod(ArrayList<String> arrayList) {
        arrayList.add("second");
        return arrayList;
    }
}
