package com.example.facebookapi.controller;

import com.example.facebookapi.dto.SignUpDto;
import com.example.facebookapi.dto.UserDto;
import com.example.facebookapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto save(@RequestBody @Valid SignUpDto userDto) {
         return userService.saveUser(userDto);

    }

    @PutMapping("/{userID}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String update(@PathVariable("userID") int userID) {
        return userService.changeActive(userID);
    }

    @GetMapping
    public List<UserDto> get() {
        return userService.getAllUsersWithPosts();
    }

    @GetMapping("/list")
    public List<String> getUserNamesList() {
        return userService.getUserNamesList();
    }

    @GetMapping("/{userID}")
    public UserDto getByUserID(@PathVariable("userID") int userID) {
        return userService.getUser(userID);
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody @Valid SignUpDto userDto){
        return userService.login(userDto);
    }

    @DeleteMapping("/{userID}")
    public List<UserDto> deleteUser(@PathVariable("userID") int userID){
        return userService.deleteUser(userID);
    }
}
