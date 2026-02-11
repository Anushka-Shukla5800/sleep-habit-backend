package com.anushka.sleep_app_backend.dto;

import lombok.Data;

@Data
public class HabitInsightDTO {

    private String habitName;
    private MetricComparisonDTO totalSleep;
    private MetricComparisonDTO deepSleep;
    private SampleSizeDTO sampleSize;
    private String interpretation;

    public HabitInsightDTO(String habitName, MetricComparisonDTO totalSleep, MetricComparisonDTO deepSleep, SampleSizeDTO sampleSize,  String interpretation) {
        this.habitName = habitName;
        this.totalSleep = totalSleep;
        this.deepSleep = deepSleep;
        this.sampleSize = sampleSize;
        this.interpretation = interpretation;

    }

}
