package com.chethan.taskportal.globalException;

public class ResourceNotFoundException
        extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);

    }
}