package com.rcelik.sia.chaptersix.api;

import com.rcelik.sia.chaptersix.data.OrderRepository;
import com.rcelik.sia.chaptersix.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/orders")
@Slf4j
@CrossOrigin("*")
public class OrderController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        log.debug("[OrderController]");
        this.orderRepository = orderRepository;
    }

    @PutMapping(consumes = "application/json", path = "/{orderId}")
    public final ResponseEntity<Order> updateOrder(@RequestBody Order order,
                                                   @PathVariable(name = "orderId") Long id) {
        log.debug("[updateOrder] id {}, order: {}", id, order);
        Optional<Order> foundOrder = orderRepository.findById(id);

        return foundOrder.map( value -> {
            log.debug("[updateOrder] found order: {}", value);
            order.setId(id); // to update the entry so need to set its primary key, otherwise it will add new one
            return new ResponseEntity<>(orderRepository.save(order), HttpStatus.OK);
        }).orElseGet(() -> {
            log.debug("[updateOrder] found order: {}", order);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        });
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public final ResponseEntity<Order> patchOrder(@PathVariable(name = "orderId") Long orderId,
                                                  @RequestBody Order patchedOrderValue){
        log.debug("[patchOrder] id: {}, order: {}", orderId, patchedOrderValue);

        Optional<Order> foundOrder = orderRepository.findById(orderId);

        return foundOrder.map( order -> {
            log.debug("[patchOrder] order with id {} is found", orderId);

            if (patchedOrderValue.getDeliveryName() != null) {
                order.setDeliveryName(patchedOrderValue.getDeliveryName());
            }
            if (patchedOrderValue.getDeliveryStreet() != null) {
                order.setDeliveryStreet(patchedOrderValue.getDeliveryStreet());
            }
            if (patchedOrderValue.getDeliveryCity() != null) {
                order.setDeliveryCity(patchedOrderValue.getDeliveryCity());
            }
            if (patchedOrderValue.getDeliveryState() != null) {
                order.setDeliveryState(patchedOrderValue.getDeliveryState());
            }
            if (patchedOrderValue.getDeliveryZip() != null) {
                order.setDeliveryZip(patchedOrderValue.getDeliveryState());
            }
            if (patchedOrderValue.getCcNumber() != null) {
                order.setCcNumber(patchedOrderValue.getCcNumber());
            }
            if (patchedOrderValue.getCcExpiration() != null) {
                order.setCcExpiration(patchedOrderValue.getCcExpiration());
            }
            if (patchedOrderValue.getCcCVV() != null) {
                order.setCcCVV(patchedOrderValue.getCcCVV());
            }
            order.setId(orderId);

            return new ResponseEntity<>(orderRepository.save(order), HttpStatus.OK);
        }).orElseGet( () -> {
            log.debug("[patchOrder] cannot find order with id: {}", orderId);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        });
    }

    @GetMapping
    public final Iterable<Order> getOrders(){
        log.debug("[getOrders]");
        return orderRepository.findAll();
    }

    @DeleteMapping(path = "/{orderId}")
    public ResponseEntity<Order> deleteOrderById(@PathVariable(name = "orderId") Long id){
        log.debug("[deleteOrderById] id: {}", id);

        try {
            orderRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException e) {
            log.error("[deleteOrderById] cannot be deleted since {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
