package com.example.domitoryproject.diet;

import com.example.domitoryproject.login.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CommentController {

    @Autowired
    DietRepository dietRepository;

    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/comment/create/{dietId}")
    public String createComment(@PathVariable Long dietId, Model model, @SessionAttribute("login") Login login) {
        Comment comment = new Comment();

        if (login.getId() == null) {
            model.addAttribute("message", "Login to create comment");
            return "redirect:/diet/" + dietId;
        } else {
            model.addAttribute("comment", comment);
            model.addAttribute("dietId", dietId);
        }

        return "comment/create";
    }

    @PostMapping("/comment/create/{dietId}")
    public String createComment(@ModelAttribute Comment comment,
                                BindingResult result,
                                @PathVariable Long dietId,
                                @SessionAttribute("login") Login login) {
        Diet diet = dietRepository.findById(dietId);
        comment.setOwner(diet);
        comment.setLogin(login);
        comment.setSympathy(0l);

        commentRepository.save(comment);

        return "redirect:/diet/" + dietId;
    }

    @GetMapping("/comment/update/{commentId}/{dietId}")
    public String updateComment(@PathVariable Long commentId, @PathVariable Long dietId, Model model) {
        Optional<Comment> commentById = commentRepository.findById(commentId);
        Comment commentDB = commentById.get();

        model.addAttribute("dietId", dietId);
        model.addAttribute("commentId",commentId);
        model.addAttribute("comment", commentDB);

        return "comment/updateForm";
    }

    @PostMapping("/comment/update/{commentId}/{dietId}")
    public String updateComment(@ModelAttribute Comment comment, @PathVariable Long commentId, @PathVariable Long dietId) {
        Optional<Comment> commentById = commentRepository.findById(commentId);
        Comment commentDB = commentById.get();

        commentDB.setSubject(comment.getSubject());
        commentDB.setContent(comment.getContent());
        commentDB.setRating(comment.getRating());
        commentDB.setSympathy(0l);

        commentRepository.save(comment);

        return "redirect:/diet/" + dietId;
    }

    @GetMapping("/comment/delete/{commentId}/{dietId}")
    public String deleteComment(@PathVariable Long commentId, @PathVariable Long dietId) {
        commentRepository.deleteById(commentId);
        return "redirect:/diet/" + dietId;
    }

    @GetMapping("/comment/sympathy/{commentId}/{dietId}")
    public String commentLike(@PathVariable Long commentId, @PathVariable Long dietId) {
        Optional<Comment> commentById = commentRepository.findById(commentId);
        Comment comment = commentById.get();

        comment.setSympathy(comment.getSympathy() + 1);
        commentRepository.save(comment);

//        List<Comment> commentList = commentRepository.findAll(Sort.by(Sort.Direction.DESC, "sympathy"));
//        Diet diet = dietRepository.findById(dietId);
//        diet.setCommentList(commentList);
//        dietRepository.save(diet);

        return "redirect:/diet/" + dietId;
    }
}
