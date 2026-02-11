package com.anushka.sleep_app_backend.dto;

import lombok.Data;

@Data
public class MetricComparisonDTO {

    private int withHabit;
    private int withoutHabit;
    private int difference;

    public MetricComparisonDTO(int withHabit, int withoutHabit) {
        this.withHabit = withHabit;
        this.withoutHabit = withoutHabit;
        this.difference = withHabit - withoutHabit;
    }
}
