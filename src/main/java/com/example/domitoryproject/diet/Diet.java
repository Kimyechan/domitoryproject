package com.example.domitoryproject.diet;

import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
//    @Temporal(TemporalType.DATE)
    private LocalDate date;
//    private String date;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private KindOfMeal kindOfMeal;

    @Column(name = "foods")
//    @ElementCollection(targetClass = String.class) //List<String> to DB
//    @NotEmpty
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Food> foods= new ArrayList<>();

//    @NotNull
//    @Enumerated(EnumType.ORDINAL)
//    private DayOfWeek dayOfWeek;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

    private Double ratingAverage;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public Double getRatingAverage() {
        return ratingAverage;
    }

    public void setRatingAverage(Double ratingAverage) {
        this.ratingAverage = ratingAverage;
    }
}
