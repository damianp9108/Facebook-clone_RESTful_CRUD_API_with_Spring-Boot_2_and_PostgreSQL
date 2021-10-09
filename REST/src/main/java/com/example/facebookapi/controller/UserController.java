package com.example.facebookapi.controller;

import com.example.facebookapi.entity.User;
import com.example.facebookapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/userService")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/save")
    public User saveUserMetaData(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PutMapping("/changeActive/{userID}")
        public User changeUserActive (@PathVariable("userID") UUID userID ){
            return userService.changeActive(userID);
    }

    @GetMapping("/getUserDetails")
    public List<User> getAllUserDetails(){
        return userService.getAllUsers();
    }

    @GetMapping("/getAllUsers/{userID}")
    public User getUserDetail(@PathVariable("userID") UUID userID){
        return userService.getUser(userID);
    }

}
