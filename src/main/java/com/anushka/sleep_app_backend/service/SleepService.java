package com.anushka.sleep_app_backend.service;

import com.anushka.sleep_app_backend.model.SleepSummary;
import com.anushka.sleep_app_backend.repository.SleepSummaryRepository;
import org.springframework.stereotype.Service;

@Service
public class SleepService {

    private final SleepSummaryRepository sleepSummaryRepository;


    public SleepService(SleepSummaryRepository sleepSummaryRepository) {
        this.sleepSummaryRepository = sleepSummaryRepository;
    }

    public SleepSummary saveSleepSummary(SleepSummary sleepSummary) {
        return this.sleepSummaryRepository.save(sleepSummary);
    }
}
