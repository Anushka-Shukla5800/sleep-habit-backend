package com.anushka.sleep_app_backend.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SleepSummaryRequestDto {

    private LocalDate date;
    private Integer totalSleepMinutes;
    private Integer deepSleepMinutes;
    private LocalDateTime sleepStartTime;
    private LocalDateTime sleepEndTime;

}
