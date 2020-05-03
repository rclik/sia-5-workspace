package com.rcelik.sia.chaptereight.exception;

public class ResourceNotFoundException extends RuntimeException {
    
    private final static String messageFormat = "No {} resource is found";

    public ResourceNotFoundException(String resourceType) {
        super(String.format(messageFormat, resourceType));
    }
}
