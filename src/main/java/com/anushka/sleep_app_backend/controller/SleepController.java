package com.anushka.sleep_app_backend.controller;

import com.anushka.sleep_app_backend.model.SleepSummary;
import com.anushka.sleep_app_backend.service.SleepService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/sleep")
public class SleepController {

    private final SleepService sleepService;
    public SleepController(SleepService sleepService) {
        this.sleepService = sleepService;
    }

    @PostMapping
    public ResponseEntity<SleepSummary> saveSleepSummary(@RequestBody SleepSummary sleepSummary) {
        SleepSummary save = this.sleepService.saveSleepSummary(sleepSummary);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }
}
