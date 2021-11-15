package com.example.facebookapi.service;

import com.example.facebookapi.dto.StatusDto;
import com.example.facebookapi.entity.Status;
import com.example.facebookapi.entity.User;
import com.example.facebookapi.exceptions.UsernameNotExist;
import com.example.facebookapi.mappers.StatusMapper;
import com.example.facebookapi.repository.StatusRepository;
import com.example.facebookapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatusService {


     private final StatusRepository statusRepository;
     private final UserRepository userRepository;
     private final StatusMapper statusMapper;

    public StatusDto saveStatus(StatusDto statusDto){
        Optional<User> userFromDB = userRepository.findByUserName(statusDto.getUserName());
        if (userFromDB.isEmpty()){
            throw new UsernameNotExist(statusDto.getUserName());
        }

        LocalDateTime time = LocalDateTime.now();
        Status newStatus = statusMapper.dtoToStatus(statusDto);
        //newStatus.setStatusID(UUID.randomUUID());
        newStatus.setUserID(userFromDB.get().getUserID());
        newStatus.setUploadTime(time);
        statusRepository.save(newStatus);

        return statusMapper.toStatusDto(newStatus);
    }

    public List<StatusDto> getAllStatus(){
        List<Status> statuses = statusRepository.findAll();
        return statusMapper.toStatusDtos(statuses);
    }
}
