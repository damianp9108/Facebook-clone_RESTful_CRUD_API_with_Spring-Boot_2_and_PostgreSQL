package com.example.facebookapi.controller;

import com.example.facebookapi.dto.StatusDto;
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


    @PostMapping
    public StatusDto save(@RequestBody @Valid StatusDto statusDto){
        return statusService.saveStatus(statusDto);
    }

    @GetMapping
    public List<StatusDto> get(){
        return statusService.getAllStatus();
    }

}
