package com.rcelik.sia.chapterfour.tacocloud.repository.ingredient;

import com.rcelik.sia.chapterfour.tacocloud.model.Ingredient;

public interface IngredientRepository {

        Iterable<Ingredient> findAll();

        Ingredient getById(String id);

        Ingredient save(Ingredient ingredient);

}
