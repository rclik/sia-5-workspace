package com.rcelik.siafive.chapterthree.order.controller;

import com.rcelik.siafive.chapterthree.constants.Attributes;
import com.rcelik.siafive.chapterthree.constants.Templates;
import com.rcelik.siafive.chapterthree.order.model.Order;
import com.rcelik.siafive.chapterthree.order.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
@Slf4j
@SessionAttributes(names = "order")
public class OrderController {

    private OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @RequestMapping("/current")
    public final String getOrderForm(Model model) {
        // just for logging what is come from session
        log.info("[getOrderForm] order: {}", model.getAttribute("order"));

        // uses order attribute which is taken from SessionAttributes
        return Templates.ORDER_FORM;
    }

    @PostMapping
    public final String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus){
        log.info("[processOrder] order: {}", order);

        if(errors.hasErrors()){
            return Templates.ORDER_FORM;
        }

        orderRepository.save(order);
        // this will reset the session
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
