package com.anushka.sleep_app_backend.controller;

import com.anushka.sleep_app_backend.model.HabitSummary;
import com.anushka.sleep_app_backend.service.HabitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/habits")
public class HabitController {

    private final HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @PostMapping
    public ResponseEntity<HabitSummary> saveHabitSummary(@RequestBody HabitSummary habitSummary){

        HabitSummary saved =  habitService.saveHabitSummary(habitSummary);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
