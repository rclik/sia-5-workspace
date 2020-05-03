package com.rcelik.sia.chaptereight.config;

import com.rcelik.sia.chaptereight.data.IngredientRepository;
import com.rcelik.sia.chaptereight.data.OrderRepository;
import com.rcelik.sia.chaptereight.data.TacoRepository;
import com.rcelik.sia.chaptereight.data.UserRepository;
import com.rcelik.sia.chaptereight.domain.Ingredient;
import com.rcelik.sia.chaptereight.domain.Ingredient.Type;
import com.rcelik.sia.chaptereight.domain.Order;
import com.rcelik.sia.chaptereight.domain.Taco;
import com.rcelik.sia.chaptereight.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Configuration
@Slf4j
@Profile("!prod")
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(IngredientRepository ingredientRepository, TacoRepository tacoRepository,
                                        PasswordEncoder passwordEncoder, UserRepository userRepository,
                                        OrderRepository orderRepository) {

        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                log.debug("[dataLoader.run]");
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
                taco1 = tacoRepository.save(taco1);

                Taco taco2 = new Taco();
                taco2.setName("Bovine Bounty");
                taco2.setIngredients(Arrays.asList(cornTortilla, groundBeef, cheddar, jack, sourCream));
                taco2 = tacoRepository.save(taco2);

                Taco taco3 = new Taco();
                taco3.setName("Veg-Out");
                taco3.setIngredients(Arrays.asList(flourTortilla, cornTortilla, tomatoes, lettuce, salsa));
                tacoRepository.save(taco3);


                User user = new User((long) 1, "admin", passwordEncoder.encode("admin"),
                        "Craig Walls", "123 North Street", "Cross Roads", "TX",
                        "76227", "123-123-1234");

                user = userRepository.save(user);

                Order order = new Order();
                order.setCcCVV("123");
                order.setCcExpiration("12/24");
                order.setCcNumber("4539317378830426");
                order.setDeliveryCity("Rize");
                order.setDeliveryStreet("FevziPasha");
                order.setDeliveryName("Delivery1");
                order.setDeliveryState("Guneysu");
                order.setDeliveryZip("53100");
                order.setTacos(Arrays.asList(taco1, taco2));
                order.setUser(user);

                orderRepository.save(order);
            }
        };
    }
}
