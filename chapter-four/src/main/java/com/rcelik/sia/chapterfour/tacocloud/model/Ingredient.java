package com.rcelik.sia.chapterfour.tacocloud.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;


// getters and setters are provided run time by lombok (Data annotation)
// and also it provides arg-constructor (RequiredArgsConstructor)
@Data
@RequiredArgsConstructor
public class Ingredient {

    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
