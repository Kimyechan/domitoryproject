package com.example.domitoryproject.system;

import com.example.domitoryproject.diet.CommentRepository;
import com.example.domitoryproject.diet.Diet;
import com.example.domitoryproject.diet.DietRepository;
import com.example.domitoryproject.diet.Food;
import com.example.domitoryproject.login.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class WelcomeController {

    @Autowired
    DietRepository dietRepository;

    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/")
    public String welcome(@SessionAttribute("login") Login login, Model model) {
        List<Diet> dietlist = dietRepository.findAll();

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        Diet currentDiet = new Diet();

        System.out.println("======================================================================");
        System.out.println(currentDate + " ////" + currentTime);
        for(Diet diet : dietlist) {
            if(diet.getDate().equals(currentDate)) {
                if(currentTime.compareTo(LocalTime.of(7,30)) >= 0 &&
                        currentTime.compareTo(LocalTime.of(12,0)) < 0 &&
                            diet.getKindOfMeal() == Diet.KindOfMeal.breakfast) {
                    currentDiet = diet;
                    break;
                }
                else if(currentTime.compareTo(LocalTime.of(12,0)) >= 0 &&
                        currentTime.compareTo(LocalTime.of(18,0)) < 0 &&
                        diet.getKindOfMeal() == Diet.KindOfMeal.lunch) {
                    currentDiet = diet;
                    break;
                }
                else if(currentTime.compareTo(LocalTime.of(18,0)) >= 0 &&
                        currentTime.compareTo(LocalTime.of(7,30)) < 0 &&
                        diet.getKindOfMeal() == Diet.KindOfMeal.dinner) {
                    currentDiet= diet;
                    break;
                }
            }
        }
        
        model.addAttribute("dietlist", dietlist);
        model.addAttribute("login", login);
        model.addAttribute("currentDiet", currentDiet);

        return "welcome";
    }
}
