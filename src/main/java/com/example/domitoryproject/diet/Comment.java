package com.example.domitoryproject.diet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Comment {

    @Id @GeneratedValue
    private Long id;

    @NotNull
    private String subject;

    @NotNull
    private String content;

    @Min(0)
    @Max(5)
    private Integer rating;

    @ManyToOne
    private Diet owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Diet getOwner() {
        return owner;
    }

    public void setOwner(Diet owner) {
        this.owner = owner;
    }
}
