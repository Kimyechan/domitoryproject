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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class WelcomeController {

    @Autowired
    DietRepository dietRepository;

    @Autowired
    CommentRepository commentRepository;

//    @GetMapping("/")
//    public String welcome(@SessionAttribute("login") Login login, Model model, SessionStatus sessionStatus) {
//        List<Diet> dietlist = dietRepository.findAll();
//        model.addAttribute("dietlist", dietlist);
//
//        model.addAttribute("loginConfirm", sessionStatus.isComplete());
//        model.addAttribute("login", login);
//
//        return "welcome";
//    }

    //    @GetMapping("/")
//    public String welcome(Model model, SessionStatus sessionStatus, HttpSession session) {
//        List<Diet> dietlist = dietRepository.findAll();
//        model.addAttribute("dietlist", dietlist);
//
//        Login login = (Login) session.getAttribute("login");
//        model.addAttribute("login", login);
//        System.out.println("===========================================================================");
//        System.out.println(login.getId());
//        return "welcome";
//    }

    @GetMapping("/")
    public String welcome(@SessionAttribute("login") Login login, Model model) {
        List<Diet> dietlist = dietRepository.findAll();
        model.addAttribute("dietlist", dietlist);
        model.addAttribute("login", login);

        System.out.println("====================================");
        System.out.println(login.getId());
        return "welcome";
    }
}
