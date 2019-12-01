package com.example.domitoryproject.diet;

import com.example.domitoryproject.login.Login;
import net.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Transactional
public class DietController {

    @Autowired
    DietRepository dietRepository;

    @Autowired
    CommentRepository commentRepository;

//    @InitBinder
//    private void dateBinder(WebDataBinder binder) {
//        //The date format to parse or output your dates
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        //Create a new CustomDateEditor
//        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
//        //Register it as custom editor for the Date type
//        binder.registerCustomEditor(Date.class, editor);
//    }

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

    @GetMapping("/diet/create")
    public String dietCreate(Model model){
        DayDiet dayDiet = new DayDiet();
        model.addAttribute("dayDiet", dayDiet);

        return "diet/adminDiet";
    }

    @PostMapping("/diet/create")
    public String dietCreate(@ModelAttribute DayDiet dayDiet){
        Diet breakfastDiet = new Diet();
        Diet lunchDiet = new Diet();
        Diet dinnerDiet= new Diet();

        String[] breakfastFoods = dayDiet.getBreakfastFoods();
        String[] lunchFoods = dayDiet.getLunchFoods();
        String[] dinnerFoods = dayDiet.getDinnerFoods();

        breakfastDiet.setDate(dayDiet.getDate());
        breakfastDiet.setKindOfMeal(Diet.KindOfMeal.breakfast);
        for(int i = 0; i < 8; i++) {
            Food food = new Food();
            food.setFood(breakfastFoods[i]);
            food.setOwner(breakfastDiet);
            breakfastDiet.getFoods().add(food);
        }
        dietRepository.save(breakfastDiet);

        lunchDiet.setDate(dayDiet.getDate());
        lunchDiet.setKindOfMeal(Diet.KindOfMeal.lunch);
        for(int i = 0; i < 8; i++) {
            Food food = new Food();
            food.setFood(lunchFoods[i]);
            food.setOwner(lunchDiet);
            lunchDiet.getFoods().add(food);
        }
        dietRepository.save(lunchDiet);

        dinnerDiet.setDate(dayDiet.getDate());
        dinnerDiet.setKindOfMeal(Diet.KindOfMeal.dinner);
        for(int i = 0; i < 8; i++) {
            Food food = new Food();
            food.setFood(dinnerFoods[i]);
            food.setOwner(dinnerDiet);
            dinnerDiet.getFoods().add(food);
        }
        dietRepository.save(dinnerDiet);

        return "redirect:/";
    }

}

































