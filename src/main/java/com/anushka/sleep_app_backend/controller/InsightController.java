package com.anushka.sleep_app_backend.controller;

import com.anushka.sleep_app_backend.service.InsightService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping(value ="api/insights")
public class InsightController {

    private final InsightService insightService;
    public InsightController(InsightService insightService) {
        this.insightService = insightService;
    }

    @GetMapping
    public Map<String,Integer> getInsights(
            @RequestParam(required = true) LocalDate startDate,
            @RequestParam(required = true) LocalDate endDate){
        return insightService.getInsightByDate(startDate,endDate);
    }
}
