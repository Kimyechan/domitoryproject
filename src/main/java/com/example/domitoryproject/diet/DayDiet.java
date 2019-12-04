package com.example.domitoryproject.diet;


import com.example.domitoryproject.system.LocalDateAttributeConverter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Convert;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DayDiet {

    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private String[] breakfastFoods= new String[8];

    private String[] lunchFoods= new String[8];

    private String[] dinnerFoods= new String[8];

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String[] getBreakfastFoods() {
        return breakfastFoods;
    }

    public void setBreakfastFoods(String[] breakfastFoods) {
        this.breakfastFoods = breakfastFoods;
    }

    public String[] getLunchFoods() {
        return lunchFoods;
    }

    public void setLunchFoods(String[] lunchFoods) {
        this.lunchFoods = lunchFoods;
    }

    public String[] getDinnerFoods() {
        return dinnerFoods;
    }

    public void setDinnerFoods(String[] dinnerFoods) {
        this.dinnerFoods = dinnerFoods;
    }
}
