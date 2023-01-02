package com.coughy.maybe.resource;

import com.coughy.maybe.entity.User;
import com.coughy.maybe.dto.Zxc;
import com.coughy.maybe.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.coughy.maybe.util.PageUtility.setPageRequest;

@RestController
@RequestMapping("/api/user")
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(userService.updateUser(user), headers, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(userService.getById(userId), headers, HttpStatus.OK);
    }

    @GetMapping("/profile/{type}")
    public ResponseEntity<Page<User>> getAllByUserProfileTitle(@RequestHeader Map<String, String> headers, @PathVariable String type) {
        Pageable pageable = setPageRequest(headers);
        return new ResponseEntity<>(userService.getAllByUserProfileTitle(type, pageable), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@PathVariable String userId) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(userService.getById(userId), headers, HttpStatus.OK);
    }

    @GetMapping("/zxc")
    public ResponseEntity<Zxc> zxc() {
        String asd = "asd.asd.qwe.ert";

        return ResponseEntity.ok(new Zxc().setString1(asd));
    }

    @GetMapping("/zxca")
    public ResponseEntity<String> zxca() {

        Boolean b = null;

        if (b) {
            return ResponseEntity.ok("true");
        } else {
            return ResponseEntity.ok("false");

        }
    }
}
