package com.rcelik.siafive.chaptereleven.domain;

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
@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 5, max = 15, message = "Taco name should be more than 5 and less than 15")
    private String name;

    @ManyToMany(targetEntity = Ingredient.class, fetch = FetchType.EAGER)
    @Size(min = 1, message = "A taco should have at least one ingredient")
    private List<Ingredient> ingredients;

    private Date createdAt;

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = new Date();
    }
}
