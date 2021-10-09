package com.example.facebookapi.service;

import com.example.facebookapi.entity.Status;
import com.example.facebookapi.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StatusService {


     private final StatusRepository statusRepository;

    public Status saveStatus(Status status){

        LocalDateTime time = LocalDateTime.now();

        status.setStatusID(UUID.randomUUID());
        status.setUploadTime(time);
        return statusRepository.save(status);
    }

    public List<Status> getAllStatus(){
        return statusRepository.findAll();
    }
}
