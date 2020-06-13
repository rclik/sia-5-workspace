package com.rcelik.siafive.chaptereleven.config;

import com.rcelik.siafive.chaptereleven.data.IngredientRepository;
import com.rcelik.siafive.chaptereleven.data.TacoRepository;
import com.rcelik.siafive.chaptereleven.data.UserRepository;
import com.rcelik.siafive.chaptereleven.domain.Ingredient;
import com.rcelik.siafive.chaptereleven.domain.Ingredient.Type;
import com.rcelik.siafive.chaptereleven.domain.Taco;
import com.rcelik.siafive.chaptereleven.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

/**
 * This class is responsible for populating some data if the application is run for development purpose
 * prod is our Spring profile for production environment
 */
@Configuration
@Profile("!prod")
@Slf4j
public class DevelopmentConfig {

    /**
     * By annotating this method bean, Spring considers it as bean and is initialized when SAC run
     * and it creates a bean for once.
     * <p>
     * By using CommandLineRunner interface as return type, Spring runs all instructions in run method.
     * So this approach can be used to make operations during initialization of SAC.
     */
    @Bean
    public CommandLineRunner dataLoader(IngredientRepository ingredientRepository,
                                        TacoRepository tacoRepository,
                                        UserRepository userRepository,
                                        PasswordEncoder passwordEncoder) {
        log.debug("[DevelopmentConfig.dataLoader] method is called");
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Ingredient flourTortilla = new Ingredient("FLTO", "Flour Tortilla", Type.WRAP);
                Ingredient cornTortilla = new Ingredient("COTO", "Corn Tortilla", Type.WRAP);
                Ingredient groundBeef = new Ingredient("GRBF", "Ground Beef", Type.PROTEIN);
                Ingredient carnitas = new Ingredient("CARN", "Carnitas", Type.PROTEIN);
                Ingredient tomatoes = new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES);
                Ingredient lettuce = new Ingredient("LETC", "Lettuce", Type.VEGGIES);
                Ingredient cheddar = new Ingredient("CHED", "Cheddar", Type.CHEESE);
                Ingredient jack = new Ingredient("JACK", "Monterrey Jack", Type.CHEESE);
                Ingredient salsa = new Ingredient("SLSA", "Salsa", Type.SAUCE);
                Ingredient sourCream = new Ingredient("SRCR", "Sour Cream", Type.SAUCE);

                ingredientRepository.save(flourTortilla);
                ingredientRepository.save(cornTortilla);
                ingredientRepository.save(groundBeef);
                ingredientRepository.save(carnitas);
                ingredientRepository.save(tomatoes);
                ingredientRepository.save(lettuce);
                ingredientRepository.save(cheddar);
                ingredientRepository.save(jack);
                ingredientRepository.save(salsa);
                ingredientRepository.save(sourCream);

                Taco taco1 = new Taco();
                taco1.setName("Carnivore");
                taco1.setIngredients(Arrays.asList(flourTortilla, groundBeef, carnitas, sourCream, salsa, cheddar));
                tacoRepository.save(taco1);

                Taco taco2 = new Taco();
                taco2.setName("Bovine Bounty");
                taco2.setIngredients(Arrays.asList(cornTortilla, groundBeef, cheddar, jack, sourCream));
                tacoRepository.save(taco2);

                Taco taco3 = new Taco();
                taco3.setName("Veg-Out");
                taco3.setIngredients(Arrays.asList(flourTortilla, cornTortilla, tomatoes, lettuce, salsa));
                tacoRepository.save(taco3);

                User user = new User();
                user.setPassword(passwordEncoder.encode("admin"));
                user.setUserName("admin");

                user = userRepository.save(user);

                log.debug("[dataLoader] user is saved: {}", user);
            }
        };
    }
}
