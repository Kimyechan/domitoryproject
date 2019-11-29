package com.example.domitoryproject.diet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Food {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Diet owner;

    private String food;

    public Diet getOwner() {
        return owner;
    }

    public void setOwner(Diet owner) {
        this.owner = owner;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }
}
