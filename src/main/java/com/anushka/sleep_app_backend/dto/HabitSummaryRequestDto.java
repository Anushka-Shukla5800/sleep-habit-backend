package com.anushka.sleep_app_backend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HabitSummaryRequestDto {

    LocalDate date;
    Boolean workedOut;
    Boolean caffeineAfter4pm;
    Boolean screenUsedWithinLastHour;
}
