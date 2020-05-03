package com.rcelik.sia.chaptereight.exception;

public class OrderNotFoundException extends ResourceNotFoundException {
    private final static String resourceType = "order";

    public OrderNotFoundException() {
        super(resourceType);
    }
}
