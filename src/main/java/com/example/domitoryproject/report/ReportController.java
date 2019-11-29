package com.example.domitoryproject.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReportController {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    DBFileRepository dbFileRepository;

    @GetMapping("/report/create")
    public String reportCreate(Model model){
        Report report = new Report();
        model.addAttribute("report", report);

        return "report/createForm";
    }

    @PostMapping("/report/create")
    public String reportCreate(@RequestParam("image") MultipartFile image, @ModelAttribute Report report) throws IOException {
//        DBFile imageDB = new DBFile(image.getOriginalFilename(), image.getBytes(), report);
//        report.setFile(imageDB);

        byte[] encode = java.util.Base64.getEncoder().encode(image.getBytes());
        String encodeString = new String(encode, "UTF-8");
        report.setData(encodeString);

//        dbFileRepository.save(imageDB);
        reportRepository.save(report);

        return "redirect:/";
    }
}
