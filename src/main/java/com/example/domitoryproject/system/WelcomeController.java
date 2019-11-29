package com.example.domitoryproject.system;

import com.example.domitoryproject.diet.CommentRepository;
import com.example.domitoryproject.diet.Diet;
import com.example.domitoryproject.diet.DietRepository;
import com.example.domitoryproject.diet.Food;
import com.example.domitoryproject.login.Login;
import com.example.domitoryproject.report.Report;
import com.example.domitoryproject.report.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
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

    @Autowired
    ReportRepository reportRepository;

    @GetMapping("/")
    public String welcome(@SessionAttribute("login") Login login, Model model) throws UnsupportedEncodingException {
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

        List<Report> reportList = reportRepository.findAll();
//        List<String> encodeList = new ArrayList<>();
//
//        for(Report report : reportList) {
//            byte[] encode = java.util.Base64.getEncoder().encode(report.getFile().getData());
//            String encodeString = new String(encode, "UTF-8");
//            encodeList.add(encodeString);
//        }
//
//        model.addAttribute("imageList", encodeList);

        model.addAttribute("dietlist", dietlist);
        model.addAttribute("login", login);
        model.addAttribute("currentDiet", currentDiet);
        model.addAttribute("reportList", reportList);


        return "welcome";
    }
}
