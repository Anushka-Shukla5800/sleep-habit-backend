package com.anushka.sleep_app_backend.service;

import com.anushka.sleep_app_backend.model.HabitSummary;
import com.anushka.sleep_app_backend.model.SleepSummary;
import com.anushka.sleep_app_backend.repository.HabitSummaryRepository;
import com.anushka.sleep_app_backend.repository.SleepSummaryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InsightService {

    private final SleepSummaryRepository sleepSummaryRepository;
    private final HabitSummaryRepository habitSummaryRepository;

    public InsightService(SleepSummaryRepository sleepSummaryRepository, HabitSummaryRepository habitSummaryRepository) {
        this.sleepSummaryRepository = sleepSummaryRepository;
        this.habitSummaryRepository = habitSummaryRepository;
    }

    public Map<String,Integer> getInsightByDate(LocalDate startDate, LocalDate endDate){

        List<SleepSummary> sleepSummaries = sleepSummaryRepository.findByDateBetween(startDate, endDate);
        List<HabitSummary> habitSummaries = habitSummaryRepository.findByDateBetween(startDate, endDate);

        Map<LocalDate, SleepSummary> sleepByDate = sleepSummaries.stream().collect(Collectors.toMap(SleepSummary::getDate, s->s));

        List<Integer> withWorkout = new ArrayList<>();
        List<Integer> withoutWorkout = new ArrayList<>();
        List<Integer> withCaffeine = new ArrayList<>();
        List<Integer> withoutCaffeine = new ArrayList<>();
        List<Integer> withScreenBeforeBed = new ArrayList<>();
        List<Integer> withoutScreenBeforeBed = new ArrayList<>();

        for(HabitSummary habit : habitSummaries){

            SleepSummary sleep = sleepByDate.get(habit.getDate());

            if(sleep == null)
                continue;

            int sleepMinutes = sleep.getTotalSleepMinutes();

            if(Boolean.TRUE.equals(habit.getWorkedOut()))
                withWorkout.add(sleepMinutes);
            else
                withoutWorkout.add(sleepMinutes);
            if(Boolean.TRUE.equals(habit.getCaffeineAfter4pm()))
                withCaffeine.add(sleepMinutes);
            else
                withoutCaffeine.add(sleepMinutes);
            if(Boolean.TRUE.equals(habit.getScreenUsedWithinLastHour()))
                withScreenBeforeBed.add(sleepMinutes);
            else
                withoutScreenBeforeBed.add(sleepMinutes);

        }

        Map<String,Integer> result = new HashMap<>();
        result.put("avgSleepWithWorkout",avg(withWorkout));
        result.put("avgSleepWithoutWorkout",avg(withoutWorkout));
        result.put("avgSleepWithCaffeine",avg(withCaffeine));
        result.put("avgSleepWithoutCaffeine",avg(withoutCaffeine));
        result.put("avgSleepWithScreen",avg(withScreenBeforeBed));
        result.put("avgSleepWithoutScreen",avg(withoutScreenBeforeBed));

        return result;

    }

    private int avg(List<Integer> values){
        return (int) values.stream().mapToInt(Integer::intValue).average().orElse(0);
    }
}
