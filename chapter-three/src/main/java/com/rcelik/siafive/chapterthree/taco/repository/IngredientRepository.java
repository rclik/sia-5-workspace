package com.rcelik.siafive.chapterthree.taco.repository;

import com.rcelik.siafive.chapterthree.taco.model.Ingredient;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Ingredient getById(String id);

    Ingredient save(Ingredient ingredient);
}
