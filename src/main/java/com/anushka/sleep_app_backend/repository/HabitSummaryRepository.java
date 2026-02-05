package com.anushka.sleep_app_backend.repository;

import com.anushka.sleep_app_backend.model.HabitSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitSummaryRepository extends JpaRepository<HabitSummary, Long> {
}
