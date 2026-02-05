package com.anushka.sleep_app_backend.service;

import com.anushka.sleep_app_backend.model.HabitSummary;
import com.anushka.sleep_app_backend.repository.HabitSummaryRepository;
import org.springframework.stereotype.Service;

@Service
public class HabitService {

    private final HabitSummaryRepository habitSummaryRepository;

    public HabitService(HabitSummaryRepository habitSummaryRepository) {
        this.habitSummaryRepository = habitSummaryRepository;
    }
    public HabitSummary saveHabitSummary(HabitSummary habitSummary) {
        return this.habitSummaryRepository.save(habitSummary);
    }
}
