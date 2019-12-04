package com.example.domitoryproject.report;

import com.example.domitoryproject.login.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ReportController {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    DBFileRepository dbFileRepository;

    @GetMapping("/report/create")
    public String reportCreate(@SessionAttribute("login") Login login, Model model){
        Report report = new Report();
        model.addAttribute("report", report);

        if (login.getId() == null) {
            model.addAttribute("message", "Login to create comment");
            return "redirect:/";
        }

        return "report/createForm";
    }

    @PostMapping("/report/create")
    public String reportCreate(@SessionAttribute("login") Login login,
                               @RequestParam("image") MultipartFile image,
                               @ModelAttribute Report report,
                               Model model) throws IOException {

        byte[] encode = java.util.Base64.getEncoder().encode(image.getBytes());
        String encodeString = new String(encode, "UTF-8");
        report.setData(encodeString);
        report.setUsername(login.getName());

        reportRepository.save(report);

        return "redirect:/";
    }
}
