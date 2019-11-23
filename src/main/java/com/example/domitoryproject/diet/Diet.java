package com.example.domitoryproject.diet;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
public class Diet {

    public enum KindOfMeal {
        breakfast, lunch, dinner;

        private Integer value;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

    public enum DayOfWeek {
        Mon, Tue, Wen, Tur, Fri, Sta, Sun;

        private Integer value;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

    @Id @GeneratedValue
    private Long id;

    @NotNull
//    @Temporal(TemporalType.DATE)
//    private Date date;
    private String date;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private KindOfMeal kindOfMeal;

    @Column(name="foods")
    @ElementCollection(targetClass=String.class) //List<String> to DB
    private List<String> foods;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private DayOfWeek dayOfWeek;

    @OneToMany(mappedBy = "owner")
    private List<Comment> commentList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public KindOfMeal getKindOfMeal() {
        return kindOfMeal;
    }

    public void setKindOfMeal(KindOfMeal kindOfMeal) {
        this.kindOfMeal = kindOfMeal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getFoods() {
        return foods;
    }

    public void setFoods(List<String> foods) {
        this.foods = foods;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
