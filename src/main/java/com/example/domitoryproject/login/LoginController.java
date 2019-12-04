package com.example.domitoryproject.login;

import com.example.domitoryproject.diet.DietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    DietRepository dietRepository;

    @Autowired
    LoginRepository loginRepository;

    @GetMapping("/login/signUp")
    public String signUpForm(Model model) {
        Login login = new Login();
        model.addAttribute("login", login);

        return "login/signUpForm";
    }

    @PostMapping("/login/signUp")
    public String signUp(@Valid @ModelAttribute Login login, BindingResult result) {
        loginRepository.save(login);

        return "redirect:/";
    }

    @PostMapping("/login/signIn")
    public String signIn(@RequestParam String id, @RequestParam String password,  HttpSession session, Model model) {
        Optional<Login> loginInfoById = loginRepository.findById(id);
        Login loginInfoDB = loginInfoById.get();

        if (loginInfoById.isEmpty()) {
            model.addAttribute("loginErrorMessage", "ID NOT FOUND");
            return "redirect:/";
        }

        if (!loginInfoDB.getPassword().equals(password)) {
            model.addAttribute("loginErrorMessage", "ID NOT CORRECT");
            return "redirect:/";
        }

        session.setAttribute("login", loginInfoDB);
        return "redirect:/";
    }

    @GetMapping("/login/logout")
    public String logout(HttpSession session){
        session.setAttribute("login", new Login());
        return "redirect:/";
    }
}


























