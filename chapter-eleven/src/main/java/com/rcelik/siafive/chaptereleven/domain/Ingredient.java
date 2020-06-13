package com.rcelik.siafive.chaptereleven.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC) // java persistence requires no-arg constructor
// since our parameters are final, then need to initialize all parameters in no-arg constructor and make it available as public
public class Ingredient {
    @Id
    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
