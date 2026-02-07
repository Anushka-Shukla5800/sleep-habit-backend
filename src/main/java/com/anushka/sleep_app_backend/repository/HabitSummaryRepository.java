package com.anushka.sleep_app_backend.repository;

import com.anushka.sleep_app_backend.model.HabitSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HabitSummaryRepository extends JpaRepository<HabitSummary, Long> {
    List<HabitSummary> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
