package com.example.facebookapi.controller;

import com.example.facebookapi.entity.User;
import com.example.facebookapi.exceptions.RecordAlreadyExistsException;
import com.example.facebookapi.repository.UserRepository;
import com.example.facebookapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/save")
    public User save(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/update/{userID}")
    public User update(@PathVariable("userID") UUID userID) {
        return userService.changeActive(userID);
    }

    @GetMapping
    public List<User> get() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userID}")
    public User getByUserID(@PathVariable("userID") UUID userID) {
        return userService.getUser(userID);
    }

    @PostMapping("/login")
    public Optional<User> login(@RequestParam("username") String userName, @RequestParam("password") String password){
        return userService.login(userName, password);
    }

}
