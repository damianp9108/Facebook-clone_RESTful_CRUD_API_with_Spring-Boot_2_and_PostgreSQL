package facebookapi.rest.controller;

import facebookapi.business.dto.UserDto;
import facebookapi.business.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
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
    @ApiOperation(value = "", authorizations = { @Authorization(value = "JWT") })
    public List<UserDto> get() {
        return userService.getAllUsersWithPosts();
    }


    @GetMapping("/list")
    @ApiOperation(value = "", authorizations = { @Authorization(value = "JWT") })
    public List<String> getUserNamesList() {
        return userService.getUserNamesList();
    }


    @GetMapping("/{userId}")
    @ApiOperation(value = "", authorizations = { @Authorization(value = "JWT") })
    public UserDto getByUserId(@PathVariable("userId") int userId) {
        return userService.getUser(userId);
    }


}
