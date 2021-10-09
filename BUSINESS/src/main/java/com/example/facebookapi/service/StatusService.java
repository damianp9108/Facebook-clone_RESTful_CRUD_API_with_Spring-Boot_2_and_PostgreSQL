package com.example.facebookapi.service;

import com.example.facebookapi.entity.Status;
import com.example.facebookapi.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Service
public class StatusService {

    @Autowired
    StatusRepository statusRepository;

    public Status saveStatus(Status status){

        Date date = new Date();
        long time = date.getTime();
        Timestamp dateTime = new Timestamp(time);

        status.setStatusID(UUID.randomUUID());
        status.setUploadTime(dateTime);
        return statusRepository.save(status);
    }

    public ArrayList<Status> getAllStatus(){
        return statusRepository.findAll();
    }
}
