package com.anushka.sleep_app_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class HabitSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    LocalDate date;
    Boolean workedOut;
    Boolean caffeineAfter4pm;
    Boolean screenUsedWithinLastHour;

    public HabitSummary() {}

    public HabitSummary(LocalDate date, Boolean workedOut,  Boolean caffeineAfter4pm, Boolean screenUsedWithinLastHour) {
        super();
        this.date = date;
        this.workedOut = workedOut;
        this.caffeineAfter4pm = caffeineAfter4pm;
        this.screenUsedWithinLastHour = screenUsedWithinLastHour;
    }
}
