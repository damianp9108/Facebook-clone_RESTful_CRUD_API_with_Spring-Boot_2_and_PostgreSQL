package com.example.facebookapi.controller;

import com.example.facebookapi.entity.Status;
import com.example.facebookapi.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/statusService")
public class StatusController {

    @Autowired
    StatusService statusService;

    @PostMapping("/save")
    public Status saveStatus(@RequestBody Status status){
        return statusService.saveStatus(status);
    }

    @GetMapping("/getAllStatus")
    public List<Status> getAllStatus(){
        return statusService.getAllStatus();
    }


}
