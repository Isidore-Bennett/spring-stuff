package com.coughy.maybe.security;

import com.coughy.maybe.entity.User;
import com.coughy.maybe.resource.AuthenticateService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authenticate")
public class AuthenticateResource {

    private final AuthenticateService authenticateService;

    public AuthenticateResource(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

//    @PostMapping("")
//    public ResponseEntity<User> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
//        try {
//
//        } catch () {
//
//        }
//
//
//    }

}
