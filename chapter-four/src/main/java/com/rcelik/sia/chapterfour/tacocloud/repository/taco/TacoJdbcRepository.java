package com.rcelik.sia.chapterfour.tacocloud.repository.taco;

import com.rcelik.sia.chapterfour.tacocloud.model.Ingredient;
import com.rcelik.sia.chapterfour.tacocloud.model.Taco;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
class TacoJdbcRepository implements TacoRepository {

    private static final String INSERT_TACO = "insert into Taco (name, createdAt) values (?, ?)";
    private static final String INSERT_INGREDIENT_TO_TACO =
            "insert into Taco_Ingredients (taco, ingredients) values (?,?)";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TacoJdbcRepository(JdbcTemplate jdbcTemplate) {
        log.debug("[TacoJdbcRepository]");
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Taco save(Taco taco) {
        log.debug("[save] taco: {}", taco);
        long id = saveTaco(taco);

        for (Ingredient ingredient : taco.getIngredients()) {
            saveIngredientToTaco(id, ingredient);
        }
        return taco;
    }

    private void saveIngredientToTaco(long id, Ingredient ingredient) {
        jdbcTemplate.update(
                INSERT_INGREDIENT_TO_TACO,
                id,
                ingredient.getId());
    }

    private long saveTaco(Taco taco) {
        taco.setCreatedAt(new Date());

        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreatorFactory(
                INSERT_TACO,
                Types.VARCHAR,
                Types.TIMESTAMP
        ).newPreparedStatementCreator(Arrays.asList(taco.getName(), taco.getCreatedAt()));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, keyHolder);

        // key holder will holds key value which is generated with the given sql
        return keyHolder.getKey().longValue();
    }
}
