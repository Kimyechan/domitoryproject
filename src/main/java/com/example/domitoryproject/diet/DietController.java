package com.example.domitoryproject.diet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DietController {

    @Autowired
    DietRepository dietRepository;

    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/diet/{id}")
    public String mappingCommentlist(@PathVariable("id") Long id, Model model)
    {
        Diet diet = dietRepository.findById(id);
        Integer countComment = diet.getCommentList().size();

        model.addAttribute("diet", diet);
        model.addAttribute("countComment", countComment);

        return "diet/dietInfo";
    }
}
