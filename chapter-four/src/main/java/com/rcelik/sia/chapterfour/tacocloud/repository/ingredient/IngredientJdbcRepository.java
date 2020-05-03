package com.rcelik.sia.chapterfour.tacocloud.repository.ingredient;

import com.rcelik.sia.chapterfour.tacocloud.model.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Repository
class IngredientJdbcRepository implements IngredientRepository {

    private final JdbcTemplate jdbcTemplate;
    private final String FIND_ALL = "select id, name, type from Ingredient";
    private final String FIND_ONE = "select id, name, type from Ingredient where id=?";
    private final String INSERT_ONE = "insert into Ingredient (id, name, type) values (?,?,?)";

    @Autowired
    public IngredientJdbcRepository(JdbcTemplate jdbcTemplate) {
        log.debug("[IngredientJdbcRepository] constructor is called");
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        log.debug("[findAll]");
        return jdbcTemplate.query(
                FIND_ALL, (resultSet, i) ->
                        new Ingredient(
                                resultSet.getString("id"),
                                resultSet.getString("name"),
                                Ingredient.Type.valueOf(resultSet.getString("type")))
        );
    }

    @Override
    public Ingredient getById(String id) {
        log.debug("[getById] id: {}", id);
        return jdbcTemplate.queryForObject(FIND_ONE, this::mapRowToIngredient);
    }

    private Ingredient mapRowToIngredient(ResultSet resultSet, int i) throws SQLException {
        log.debug("[mapRowToIngredient] rowNumber: {} resultSet: {}", i, resultSet);
        return new Ingredient(
                resultSet.getString("id"),
                resultSet.getString("name"),
                Ingredient.Type.valueOf(resultSet.getString("type"))
        );
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        log.debug("[save] ingredient: {}", ingredient);
        jdbcTemplate.update(INSERT_ONE, ingredient.getId(), ingredient.getName(), ingredient.getType());
        return ingredient;
    }
}
