package com.rcelik.siafive.chapterthree.taco.repository;

import com.rcelik.siafive.chapterthree.taco.model.Ingredient;
import com.rcelik.siafive.chapterthree.taco.model.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

@Repository
public class JdbcTacoRepository implements TacoRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTacoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Taco save(Taco taco) {
        long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);
        // time to save ingredients
        for (Ingredient ingredient : taco.getIngredients()) {
            saveIngredientToTaco(ingredient, tacoId);
        }

        return taco;
    }

    // this method will be used to save ingredient information to the taco in database
    private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {
        jdbcTemplate.update(
                "insert into Taco_Ingredients (taco, ingredient) values (?,?)",
                tacoId,
                ingredient.getId());
    }

    // this method creates a taco and saves it to the database and returns its id.
    // id will be used for saving ingredients to the other database table.
    private long saveTacoInfo(Taco design) {
        design.setCreatedAt(new Date());

        // preparing the sql that will be used to insert taco object to the database
        PreparedStatementCreator psc = new PreparedStatementCreatorFactory(
                "insert into Taco (name, createdAt) values (?, ?)",
                Types.VARCHAR,
                Types.TIMESTAMP
        ).newPreparedStatementCreator(
                Arrays.asList(
                        design.getName(),
                        design.getCreatedAt().getTime()
                )
        );

        // this class is used to generate primary key for the the database table
        // it is used with prepared statement creator by jdbc template update method.
        // so new primary id value is generated.
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);

        return keyHolder.getKey().longValue();
    }
}
