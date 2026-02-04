package com.anushka.sleep_app_backend.repository;

import com.anushka.sleep_app_backend.model.SleepSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SleepSummaryRepository extends JpaRepository<SleepSummary, Long> {
}
