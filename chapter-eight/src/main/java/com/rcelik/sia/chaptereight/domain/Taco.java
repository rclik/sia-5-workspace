package com.rcelik.sia.chaptereight.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@Entity
public class Taco {

    // this is required then JmsTemplate send method is used
    // private static final long serialVersionUID = -217226517796726228L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;

    @NotNull
    @Size(min = 5, max = 15, message = "Taco name should be more than 5 and less than 15")
    private String name;

    @ManyToMany(targetEntity = Ingredient.class)
    @Size(min = 1, message = "A taco should have at least one ingredient")
    private List<Ingredient> ingredients;

    private Date createdAt;

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = new Date();
    }
}
