package com.example.domitoryproject.diet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {

    @Autowired
    DietRepository dietRepository;

    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/comment/create/{dietId}")
    public String createComment(@PathVariable Long dietId, Model model){
        Comment comment = new Comment();

//        Diet diet = dietRepository.findById(dietId);
//        comment.setOwner(diet);
//
//        commentRepository.save(comment);

        model.addAttribute("comment", comment);
        model.addAttribute("dietId", dietId);

        return "comment/create";
    }

    @PostMapping("/comment/create/{dietId}")
    public String createComment(@ModelAttribute Comment comment, BindingResult result,  @PathVariable Long dietId){
        Diet diet = dietRepository.findById(dietId);
        comment.setOwner(diet);
        commentRepository.save(comment);

        return "redirect:/diet/" + dietId;
    }
}
