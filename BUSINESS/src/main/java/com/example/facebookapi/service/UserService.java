package com.example.facebookapi.service;

import com.example.facebookapi.entity.User;
import com.example.facebookapi.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    @Getter
    @Setter
    private boolean condition = false;

    public void saveUser(User user)  {
        Optional<User> userFromDB = userRepository.findByUserName(user.getUserName());
        if (userFromDB.isPresent()) {
            condition = true;
        }else {

            LocalDateTime dateTime = LocalDateTime.now();
            user.setUserID(UUID.randomUUID());
            user.setActive(false);
            user.setJoiningDate(dateTime);

            userRepository.save(user);
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(UUID userID) {
        return userRepository.findByUserID(userID);
    }

    public User changeActive(UUID userID) {
        User userToChangeActive = getUser(userID);
        boolean activity = userToChangeActive.isActive();
        userToChangeActive.setActive(!activity);

        return userRepository.save(userToChangeActive);

    }


    public void login(String userName, String password){
        Optional<User> userFromDb = userRepository.findByUserName(userName);

        if(userFromDb.isEmpty() || wrongPassword(userFromDb, password)){
            condition = true;
        }
    }

    private boolean wrongPassword(Optional<User>userFromDb, String password) {
        return !userFromDb.get().getPassword().equals(password);
    }

}
