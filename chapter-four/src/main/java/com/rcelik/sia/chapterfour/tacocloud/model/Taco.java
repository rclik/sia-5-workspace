package com.rcelik.sia.chapterfour.tacocloud.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class Taco {

    @NotNull
    @Size(min = 5, max = 15, message = "Name must be at least 5 character long")
    private String name;
    private List<Ingredient> ingredients;

    private long id;
    private Date createdAt;
}
