package com.rcelik.siafive.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

// when added data ann. then final class instances with no initialization does not give compile time error
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
