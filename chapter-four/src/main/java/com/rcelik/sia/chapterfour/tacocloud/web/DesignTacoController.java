package com.rcelik.sia.chapterfour.tacocloud.web;

import com.rcelik.sia.chapterfour.tacocloud.model.Ingredient;
import com.rcelik.sia.chapterfour.tacocloud.model.Order;
import com.rcelik.sia.chapterfour.tacocloud.model.Taco;
import com.rcelik.sia.chapterfour.tacocloud.repository.ingredient.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes(names = "order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public final String loadDesignView(Model model){
        log.debug("[loadDesignView] model: {}", model);
        loadIngredientsToModel(model);
        return "design";
    }

    @PostMapping
    public final String processDesign(@Valid Taco taco, Errors errors, Model model){
        log.debug("[processDesign] taco: {}", taco);
        log.debug("[processDesign] model: {}", model);
        log.debug("[processDesign] errors: {}", errors);

        if(errors.hasErrors()){
            loadIngredientsToModel(model);
            return "design";
        }

        return "redirect:/orders/current";
    }

    // this is model object for this view
    // it is created every request made to the view
    @ModelAttribute( name = "taco")
    public final Taco generateTacoModelObject(){
        log.debug("[generateTacoModelObject] taco model object is created");
        return new Taco();
    }

    // this model object for this controller
    // it is created for a session, it will be deleted when session attribute is deleted
    // to map this model attribute to session the annotation SessionAttributes is used with model object name
    // to delete this attribute from session, you can delete it by getting from attribute. you can achieve it
    // SessionStatus object s setComplete()
    @ModelAttribute(name = "order")
    public final Order generateOrderModelObject(){
        log.debug("[generateOrderModelObject] order model object is generated for session");
        return new Order();
    }

    private void loadIngredientsToModel(Model model) {
        log.debug("[loadIngredientsToModel]");
        List<Ingredient> ingredients = new ArrayList<>();

        ingredientRepository.findAll().forEach( ingredient -> ingredients.add(ingredient));

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type: types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type){
        return ingredients.stream()
                .filter(ingredient -> ingredient.getType() == type)
                .collect(Collectors.toList());
    }
}
