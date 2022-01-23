package facebookapi.rest.controller;

import facebookapi.business.dto.UserDto;
import facebookapi.business.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    @GetMapping
    public List<UserDto> get() {
        return userService.getAllUsersWithPosts();
    }


    @GetMapping("/list")
    public List<String> getUserNamesList() {
        return userService.getUserNamesList();
    }


    @GetMapping("/{userId}")
    public UserDto getByUserId(@PathVariable("userId") int userId) {
        return userService.getUser(userId);
    }


}
