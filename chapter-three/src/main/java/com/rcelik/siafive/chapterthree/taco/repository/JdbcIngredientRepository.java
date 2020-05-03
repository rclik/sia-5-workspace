package com.rcelik.siafive.chapterthree.taco.repository;

import com.rcelik.siafive.chapterthree.taco.model.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@Slf4j
public class JdbcIngredientRepository implements IngredientRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        log.info("[findAll]");
        String query = "select id, name, type from Ingredient";

        return jdbcTemplate.query(query, this::mapRowToIngredients);
    }

//    @Override
//    public Ingredient getById(String id) {
//        log.info("[getById]");
//        String query = "select id, name, type from Ingredient where id=?";
//        return jdbcTemplate.queryForObject(query, this::mapRowToIngredients, id);
//    }

    @Override
    public Ingredient getById(String id) {
        log.info("[getById]");
        String query = "select id, name, type from Ingredient where id=?";

        return jdbcTemplate.queryForObject(query,
                new RowMapper<Ingredient>() {
                    @Override
                    public Ingredient mapRow(ResultSet resultSet, int i) throws SQLException {
                        return new Ingredient(
                                resultSet.getString("id"),
                                resultSet.getString("name"),
                                Ingredient.Type.valueOf(resultSet.getString("type"))
                        );
                    }
                }, id);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        log.info("[save] ingredient: {}", ingredient);
        jdbcTemplate.update(
                "insert into Ingredient (id, name, type) values (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString()
        );

        return ingredient;
    }

    private Ingredient mapRowToIngredients(ResultSet resultSet, int rowNumber) throws SQLException {
        log.info("[mapRowToIngredient] resultRest: {}, rowNumber: {}", resultSet, rowNumber);
        return new Ingredient(
                resultSet.getString("id"),
                resultSet.getString("name"),
                Ingredient.Type.valueOf(resultSet.getString("type"))
        );
    }
}
