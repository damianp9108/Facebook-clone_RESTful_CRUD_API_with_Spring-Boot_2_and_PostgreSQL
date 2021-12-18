package facebookapi.rest.controller;

import facebookapi.business.dto.NewUserDto;
import facebookapi.business.dto.UserDto;
import facebookapi.business.service.UserService;
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
    public UserDto save(@RequestBody @Valid NewUserDto userDto) {
         return userService.saveUser(userDto);

    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String update(@PathVariable("userId") int userId) {
        return userService.changeActive(userId);
    }

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

    @PostMapping("/login")
    public UserDto login(@RequestBody @Valid NewUserDto userDto){
        return userService.login(userDto);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") int userId){
        return userService.deleteUser(userId);
    }
}
