package com.anushka.sleep_app_backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class SleepSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate date;
    private Integer sleepScore;
    private Integer totalSleepMinutes;
    private Integer deepSleepMinutes;
    private LocalDateTime sleepStartTime;
    private LocalDateTime sleepEndTime;



}
