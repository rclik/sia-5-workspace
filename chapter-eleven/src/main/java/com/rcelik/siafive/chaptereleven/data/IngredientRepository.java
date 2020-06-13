package com.rcelik.siafive.chaptereleven.data;

import com.rcelik.siafive.chaptereleven.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
