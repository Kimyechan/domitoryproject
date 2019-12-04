package com.example.domitoryproject.system;

import com.example.domitoryproject.diet.CommentRepository;
import com.example.domitoryproject.diet.Diet;
import com.example.domitoryproject.diet.DietRepository;
import com.example.domitoryproject.login.Login;
import com.example.domitoryproject.report.Report;
import com.example.domitoryproject.report.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class WelcomeController {

    @Autowired
    DietRepository dietRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ReportRepository reportRepository;

    @GetMapping("/")
    public String welcome(@SessionAttribute("login") Login login, Model model) throws UnsupportedEncodingException {
        List<Diet> dietlist = dietRepository.findAllByOrderByDateAsc();

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        Diet currentDiet = new Diet();
//        Diet breakfastDiet = new Diet();
//        Diet lunchDiet = new Diet();
//        Diet dinnerDiet = new Diet();
//
//        Diet tmpdiet = new Diet();
//
//        for(Diet diet : dietlist) {
//            if(diet.getDate().equals(currentDate)) {
//                if(currentTime.compareTo(LocalTime.of(0,0)) >= 0 &&
//                        currentTime.compareTo(LocalTime.of(12,0)) < 0 &&
//                            diet.getKindOfMeal() == Diet.KindOfMeal.breakfast) {
//                    currentDiet = diet;
//                    break;
//                }
//                else if(currentTime.compareTo(LocalTime.of(12,0)) >= 0 &&
//                        currentTime.compareTo(LocalTime.of(18,0)) < 0 &&
//                        diet.getKindOfMeal() == Diet.KindOfMeal.lunch) {
//                    currentDiet = diet;
//                    break;
//                }
//                else if(currentTime.compareTo(LocalTime.of(18,0)) >= 0 &&
//                        currentTime.compareTo(LocalTime.of(0,0)) < 0 &&
//                        diet.getKindOfMeal() == Diet.KindOfMeal.dinner) {
//                    currentDiet= diet;
//                    break;
//                }
//
//                if(diet.getKindOfMeal() == Diet.KindOfMeal.breakfast) {
//                    breakfastDiet = diet;
//                }
//                else if(diet.getKindOfMeal() == Diet.KindOfMeal.lunch) {
//                    lunchDiet = diet;
//                }
//                else if(diet.getKindOfMeal() == Diet.KindOfMeal.dinner) {
//                    dinnerDiet = diet;
//                }
//            }
//        }
        for(Diet diet : dietlist) {
            if(diet.getDate().equals(currentDate)) {
                if(currentTime.compareTo(LocalTime.of(0,0)) >= 0 &&
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
                        currentTime.compareTo(LocalTime.of(0,0)) < 0 &&
                        diet.getKindOfMeal() == Diet.KindOfMeal.dinner) {
                    currentDiet= diet;
                    break;
                }
            }
        }

        List<Report> reportList = reportRepository.findAll();

        model.addAttribute("dietlist", dietlist);
        model.addAttribute("login", login);
        model.addAttribute("currentDiet", currentDiet);
        model.addAttribute("reportList", reportList);
//        model.addAttribute("breakfastDiet", breakfastDiet);
//        model.addAttribute("lunchDiet", lunchDiet);
//        model.addAttribute("dinnerDiet", dinnerDiet);
        model.addAttribute("currentDate", currentDate);

        return "welcome";
    }
}
