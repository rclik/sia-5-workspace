package com.rcelik.siafive.web;

import com.rcelik.siafive.model.Ingredient;
import com.rcelik.siafive.model.Ingredient.Type;
import com.rcelik.siafive.model.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    private static final String VIEW_NAME = "design";

    private List<Ingredient> ingredients = Arrays.asList(
            new Ingredient("FLTO", "Flour Tortilla", Type.WRAP), new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
            new Ingredient("GRBF", "Ground Beef", Type.PROTEIN), new Ingredient("CARN", "Carnitas", Type.PROTEIN),
            new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES), new Ingredient("LETC", "Lettuce", Type.VEGGIES),
            new Ingredient("CHED", "Cheddar", Type.CHEESE),
            new Ingredient("JACK", "Monterrey Jack", Type.CHEESE), new Ingredient("SLSA", "Salsa", Type.SAUCE),
            new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
    );

    @GetMapping
    public String showDesignForm(Model model){
        // adding type specific ingredients to the view model.
        Type [] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(type));
        }

        // add an empty taco object to the view model.
        model.addAttribute(VIEW_NAME, new Taco());


        model.addAttribute("name", "rahman");

        return VIEW_NAME;
    }

    @PostMapping
    public String processDesign(@Valid Taco taco, Errors errors){
        // error validation occurs then return design template
        if(errors.hasErrors()){
            return "design";
        }
        // taco taco object is received here
        log.info("[processDesign] taco: {}", taco);
        // redirect: prefix make Spring to create redirect the request to another url which is /orders/current
        return "redirect:/orders/current"; // then the page is redirected to another url by Spring Boot
    }

    // return ingredients of given type
    private List<Ingredient> filterByType(Type type){
        return ingredients.stream().filter( ingredient -> ingredient.getType() == type).collect(Collectors.toList());
    }
}
