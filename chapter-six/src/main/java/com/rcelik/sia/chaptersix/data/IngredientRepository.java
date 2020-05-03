package com.rcelik.sia.chaptersix.data;

import com.rcelik.sia.chaptersix.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
