package com.example.facebookapi.service;

import com.example.facebookapi.entity.User;
import com.example.facebookapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User submitMetaDataOfUser(User user){

        LocalDateTime dateTime = LocalDateTime.now();

        user.setActive(false);
        user.setJoiningDate(dateTime);

        return userRepository.save(user);
    }

    public ArrayList<User> retrieveAllUserDetails(){
        return userRepository.findAll();
    }

    public User getUserData(String userID){
        return userRepository.findAllByUserID(userID);
    }

    public User changeUserActive(String userID){
        User userToChangeActive = getUserData(userID);
        boolean activity = userToChangeActive.isActive();
        userToChangeActive.setActive(!activity);

        return userRepository.save(userToChangeActive);

    }

}
