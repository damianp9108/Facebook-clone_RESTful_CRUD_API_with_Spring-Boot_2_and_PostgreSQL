package facebookapi.rest.controller;

import facebookapi.business.payload.request.LoginRequest;
import facebookapi.business.payload.request.SignupRequest;
import facebookapi.business.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;



    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.authenticateUser(loginRequest);
    }


    @PostMapping("/signup")
    public String registerUser(@Valid @RequestBody SignupRequest signupRequest) {
       return userService.saveUser(signupRequest);

    }



}
