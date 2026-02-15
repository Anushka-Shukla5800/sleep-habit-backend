package com.anushka.sleep_app_backend.service;

import com.anushka.sleep_app_backend.dto.HabitInsightDTO;
import com.anushka.sleep_app_backend.dto.MetricComparisonDTO;
import com.anushka.sleep_app_backend.dto.SampleSizeDTO;
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
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class InsightService {

    private final SleepSummaryRepository sleepSummaryRepository;
    private final HabitSummaryRepository habitSummaryRepository;

    public InsightService(SleepSummaryRepository sleepSummaryRepository, HabitSummaryRepository habitSummaryRepository) {
        this.sleepSummaryRepository = sleepSummaryRepository;
        this.habitSummaryRepository = habitSummaryRepository;
    }

    public List<HabitInsightDTO> getInsightByDate(LocalDate startDate, LocalDate endDate){

        List<SleepSummary> sleepSummaries = sleepSummaryRepository.findByDateBetween(startDate, endDate);
        List<HabitSummary> habitSummaries = habitSummaryRepository.findByDateBetween(startDate, endDate);

        Map<LocalDate, SleepSummary> sleepByDate = sleepSummaries.stream().collect(Collectors.toMap(SleepSummary::getDate, s->s));


        return List.of( buildInsight("Workout", sleepByDate, habitSummaries, HabitSummary::getWorkedOut),
                buildInsight("Caffeine", sleepByDate, habitSummaries, HabitSummary::getCaffeineAfter4pm),
                buildInsight("ScreenTime", sleepByDate, habitSummaries, HabitSummary::getScreenUsedWithinLastHour));

    }

    private HabitInsightDTO buildInsight(String habitName,
                                         Map<LocalDate,SleepSummary> sleepByDate,
                                         List<HabitSummary> habits,
                                         Function<HabitSummary, Boolean> habitCondition){

        List<Integer> totalWith = new ArrayList<>();
        List<Integer> totalWithout = new ArrayList<>();

        List<Integer> deepWith = new ArrayList<>();
        List<Integer> deepWithout = new ArrayList<>();

        for(HabitSummary habit : habits){

            SleepSummary sleep = sleepByDate.get(habit.getDate());
            if(sleep == null)
                continue;

            Boolean hasHabit = Boolean.TRUE.equals(habitCondition.apply(habit));

            if(hasHabit) {
                totalWith.add(sleep.getTotalSleepMinutes());
                deepWith.add(sleep.getDeepSleepMinutes());
            }else {
                totalWithout.add(sleep.getTotalSleepMinutes());
                deepWithout.add(sleep.getDeepSleepMinutes());
            }
        }

        MetricComparisonDTO totalSleep = new MetricComparisonDTO(avg(totalWith),avg(totalWithout));
        MetricComparisonDTO deepSleep = new MetricComparisonDTO(avg(deepWith),avg(deepWithout));

        SampleSizeDTO sampleSize = new SampleSizeDTO(totalWith.size(),totalWithout.size());
        String interpretation = generateInterpretation(habitName, totalSleep, deepSleep);

        return new HabitInsightDTO(
                habitName,
                totalSleep,
                deepSleep,
                sampleSize,
                interpretation);

    }

    private String  generateInterpretation(String habitName,MetricComparisonDTO totalSleep, MetricComparisonDTO deepSleep) {

        if(totalSleep.getDifference()>0 && deepSleep.getDifference()>0)
            return "You tend to sleep longer and get more deep sleep on days with "+habitName.toLowerCase()+".";
        else if(totalSleep.getDifference()<0 && deepSleep.getDifference()<0)
            return "You tend to sleep less and get less deep sleep on days with "+habitName.toLowerCase()+".";
        else{
            return "The impact of "+habitName.toLowerCase()+" appears to be mixed.";
        }

    }

    private int avg(List<Integer> values){
        return (int) values.stream().mapToInt(Integer::intValue).average().orElse(0);
    }
}
