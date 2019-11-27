package com.example.domitoryproject.diet;

import com.example.domitoryproject.login.Login;
import net.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class DietController {

    @Autowired
    DietRepository dietRepository;

    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/diet/{id}")
    public String mappingCommentlist(@PathVariable("id") Long id, Model model, @SessionAttribute("login") Login login)
    {
        Diet diet = dietRepository.findById(id);
        Integer countComment = diet.getCommentList().size();
        List<Comment> commentList = diet.getCommentList();
        Double ratingSum = 0.0;
        Double ratingAverage;

        for (Comment comment: commentList)
            ratingSum = ratingSum + comment.getRating();

        if(countComment != 0)
            ratingAverage = ratingSum/countComment;
        else
            ratingAverage = 0.0;

        diet.setRatingAverage(ratingAverage);

        //내림차순 정렬
        commentList.sort(new Comparator<Comment>() {
            @Override
            public int compare(Comment arg0, Comment arg1) {
                // TODO Auto-generated method stub
                Long sympathy0 = arg0.getSympathy();
                Long sympathy1 = arg1.getSympathy();

                if(sympathy0 == sympathy1) return 0;
                else if(sympathy0 < sympathy1) return 1;
                else return -1;
            }
        });

        diet.setCommentList(commentList);
        dietRepository.save(diet);

        model.addAttribute("diet", diet);
        model.addAttribute("countComment", countComment);
        model.addAttribute("login", login);

        return "diet/dietInfo";
    }
}
