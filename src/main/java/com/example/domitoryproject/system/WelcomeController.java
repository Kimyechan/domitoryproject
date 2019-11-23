package com.example.domitoryproject.system;

import com.example.domitoryproject.diet.CommentRepository;
import com.example.domitoryproject.diet.Diet;
import com.example.domitoryproject.diet.DietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WelcomeController {

    @Autowired
    DietRepository dietRepository;

    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/")
    public String welcome() {
        for (Diet.DayOfWeek dayOfWeek : Diet.DayOfWeek.values()) {
            for (Diet.KindOfMeal kindOfMeal : Diet.KindOfMeal.values()) {
                Diet newDiet1 = new Diet();
                newDiet1.setKindOfMeal(kindOfMeal);
                newDiet1.setDate("2019-11-25");
                newDiet1.setDayOfWeek(dayOfWeek);

                List<String> foods1 = new ArrayList<>();
                foods1.add("rice");
                foods1.add("bibimbob");

                newDiet1.setFoods(foods1);

                dietRepository.save(newDiet1);
            }
        }

        return "welcome";
    }
}
