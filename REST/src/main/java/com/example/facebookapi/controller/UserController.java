package com.example.facebookapi.controller;

import com.example.facebookapi.dto.UserDto;
import com.example.facebookapi.entity.User;
import com.example.facebookapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto save(@RequestBody @Valid User user) {
         return userService.saveUser(user);

    }

    @PutMapping("/update/{userID}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String update(@PathVariable("userID") UUID userID) {
        return userService.changeActive(userID);
    }

    @GetMapping
    public List<UserDto> get() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userID}")
    public UserDto getByUserID(@PathVariable("userID") UUID userID) {
        return userService.getUser(userID);
    }

    @PostMapping("/login")
    public UserDto login(@RequestParam("username") @NotBlank(message = "nie podano nazwy uzytkownika") String userName, @RequestParam("password") @NotBlank(message = "haslo jest wymagane") String password){
        return userService.login(userName, password);
    }

}
