package com.anushka.sleep_app_backend.repository;

import com.anushka.sleep_app_backend.model.SleepSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SleepSummaryRepository extends JpaRepository<SleepSummary, Long> {
    List<SleepSummary> findByDateBetween(LocalDate startDate, LocalDate endDate);

}
