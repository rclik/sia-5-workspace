package com.rcelik.sia.chaptereight.taccloudkitchen.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class Taco {
    private String name;

    private Date createdAt;

    private List<Ingredient> ingredients;

}
