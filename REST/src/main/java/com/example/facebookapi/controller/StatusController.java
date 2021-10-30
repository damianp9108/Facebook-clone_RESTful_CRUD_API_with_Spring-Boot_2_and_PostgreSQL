package com.example.facebookapi.controller;

import com.example.facebookapi.entity.Status;
import com.example.facebookapi.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statuses")
public class StatusController {

    private final StatusService statusService;


    @PostMapping("/save")
    public Status save(@RequestBody @Valid Status status){
        return statusService.saveStatus(status);
    }

    @GetMapping
    public List<Status> get(){
        return statusService.getAllStatus();
    }


}
