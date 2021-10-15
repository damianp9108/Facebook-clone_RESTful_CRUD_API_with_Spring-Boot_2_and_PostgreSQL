package com.example.facebookapi.controller;

import com.example.facebookapi.entity.User;
import com.example.facebookapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody User user) {
        userService.saveUser(user);
        if (userService.isCondition()) {
            userService.setCondition(false);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }


        return ResponseEntity.ok(user);
    }

    @PutMapping("/update/{userID}")
    public User update(@PathVariable("userID") UUID userID) {
        return userService.changeActive(userID);
    }

    @GetMapping
    public ResponseEntity get() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userID}")
    public User getByUserID(@PathVariable("userID") UUID userID) {
        return userService.getUser(userID);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestParam("username") String userName, @RequestParam("password") String password){
        userService.login(userName, password);
        if (userService.isCondition()){
            userService.setCondition(false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok("Zalogowano pomyślnie");
    }





   /* @PatchMapping("/password")
    public ResponseEntity changePassword(@RequestBody User user, @RequestHeader("nowe_haslo") String newPassword){
        Optional<User> userFromDb = userRepository.findByUsername(user.getUsername());

        if(userFromDb.isEmpty() || wrongPassword(userFromDb, user)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        userFromDb.get().setPassword(newPassword);
        User updatedUser = userRepository.save(userFromDb.get());

        return ResponseEntity.ok(updatedUser);

   }
    */

}
