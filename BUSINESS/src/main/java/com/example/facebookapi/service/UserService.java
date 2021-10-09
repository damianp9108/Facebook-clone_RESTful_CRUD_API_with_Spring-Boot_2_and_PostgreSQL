package com.example.facebookapi.service;

import com.example.facebookapi.entity.User;
import com.example.facebookapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    public User saveUser(User user){

        LocalDateTime dateTime = LocalDateTime.now();
        user.setUserID(UUID.randomUUID());
        user.setActive(false);
        user.setJoiningDate(dateTime);

        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUser(UUID userID){
        return userRepository.findByUserID(userID);
    }

    public User changeActive(UUID userID){
        User userToChangeActive = getUser(userID);
        boolean activity = userToChangeActive.isActive();
        userToChangeActive.setActive(!activity);

        return userRepository.save(userToChangeActive);

    }

}
