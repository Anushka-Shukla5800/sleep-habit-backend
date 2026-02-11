package com.anushka.sleep_app_backend.dto;

import lombok.Data;

@Data
public class SampleSizeDTO {

    private int withHabit;
    private int withoutHabit;

    public SampleSizeDTO(int withHabit, int withoutHabit) {
        this.withHabit = withHabit;
        this.withoutHabit = withoutHabit;
    }
}
