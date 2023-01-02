package com.coughy.maybe.resource;

import com.coughy.maybe.entity.User;
import com.coughy.maybe.model.AuthenticationRequest;
import com.coughy.maybe.model.AuthenticationResponse;
import com.coughy.maybe.security.JwtUtility;
import com.coughy.maybe.service.AccountService;
import com.coughy.maybe.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountResource {

    private final AccountService accountService;
    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;
    private static final JwtUtility jwtUtility = new JwtUtility();

    public AccountResource(AccountService accountService, UserService userService, UserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
        this.accountService = accountService;
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register/{profile}")
    public ResponseEntity<User> register(@RequestBody User user, @PathVariable String profile) {
        if (!userService.existsUserByLogin(user.getLogin())) {
            User registeredUser = accountService.registerUser(user, profile);
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            ));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtility.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
//
//        return new ResponseEntity<>(accountService.verifyUser(user), headers, HttpStatus.OK);
    }
}
