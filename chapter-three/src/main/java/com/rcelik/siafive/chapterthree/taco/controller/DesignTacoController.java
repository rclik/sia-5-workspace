package com.rcelik.siafive.chapterthree.taco.controller;

import com.rcelik.siafive.chapterthree.constants.Attributes;
import com.rcelik.siafive.chapterthree.constants.Templates;
import com.rcelik.siafive.chapterthree.order.model.Order;
import com.rcelik.siafive.chapterthree.taco.model.Ingredient;
import com.rcelik.siafive.chapterthree.taco.model.Ingredient.Type;
import com.rcelik.siafive.chapterthree.taco.model.Taco;
import com.rcelik.siafive.chapterthree.taco.repository.JdbcIngredientRepository;
import com.rcelik.siafive.chapterthree.taco.repository.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@SessionAttributes(names = "order")
@RequestMapping("/design")
public class DesignTacoController {

    private JdbcIngredientRepository repository;
    private TacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(JdbcIngredientRepository repository, TacoRepository tacoRepository) {
        this.repository = repository;
        this.tacoRepository = tacoRepository;
    }

    @GetMapping
    public String showDesignForm(Model model) {
        log.info("[showDesignForm] model: {}", model);
        populateModelWithIngredients(model);
        return Templates.TACO_DESIGN;
    }

    private void populateModelWithIngredients(Model model){
        // return ingredients form database
        List<Ingredient> ingredients = new ArrayList<>();

        // convey all ingredients to controller class objects
        repository.findAll().forEach( ingredient -> ingredients.add(ingredient));

        // adding type specific ingredients to the view model as key-value pair
        Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(type, ingredients));
        }
    }

    @ModelAttribute(name = "order")
    public Order order() {
        log.info("[order] order model attribute is created for session");
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        log.info("[taco] taco model attribute is created for the request");
        return new Taco();
    }

    @PostMapping
    public String processDesign(@Valid Taco taco, Errors errors, @ModelAttribute Order order, Model model){
        // taco taco object is received here
        log.info("[processDesign] taco: {}", taco);

        // error validation occurs then return design template
        if(errors.hasErrors()){
            log.info("[processDesign] there is error: {}", errors.getAllErrors());
            populateModelWithIngredients(model);
            // it is enough to set this for design template because taco object is already inside the model object
            // it is added from html post form
            return "design";
        }
        // redirect: prefix make Spring to create redirect the request to another url which is /orders/current
        return "redirect:/orders/current"; // then the page is redirected to another url by Spring Boot
    }

    // return ingredients of given type
    private List<Ingredient> filterByType(Type type, List<Ingredient> ingredients) {
        return ingredients.stream().filter(ingredient -> ingredient.getType() == type).collect(Collectors.toList());
    }

}
