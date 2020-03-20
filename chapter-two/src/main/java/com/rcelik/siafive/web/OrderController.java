package com.rcelik.siafive.web;

import com.rcelik.siafive.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/current")
    public final String orderForm(Model model){
        log.info("[orderForm] model: {}", model);

        // new attribute is added to the
        model.addAttribute("order", new Order());

        // will build new form template, orderForm.html
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors) {
        log.info("[processOrder] order: {}", order );

        if(errors.hasErrors()){
            return "orderForm";
        }

        log.info("Order submitted: " + order);
        return "redirect:/";
    }
}
