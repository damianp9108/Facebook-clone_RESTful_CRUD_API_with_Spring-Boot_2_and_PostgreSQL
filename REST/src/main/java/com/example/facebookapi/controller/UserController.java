package com.example.facebookapi.controller;

import com.example.facebookapi.entity.User;
import com.example.facebookapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("/api/userService")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/save")
    public User saveUserMetaData(@RequestBody User user){
        return userService.submitMetaDataOfUser(user);
    }

    @PutMapping("/changeActive/{userID}")
        public User changeUserActive (@PathVariable("userID") String userID ){
            return userService.changeUserActive(userID);
    }

    @GetMapping("/getUserDetails")
    public ArrayList<User> getAllUserDetails(){
        return userService.retrieveAllUserDetails();
    }

    @GetMapping("/getAllUsers/{userID}")
    public User getUserDetail(@PathVariable("userID") String userID){
        return userService.getUserData(userID);
    }

}
