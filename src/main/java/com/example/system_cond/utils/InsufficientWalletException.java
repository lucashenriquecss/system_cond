package com.example.system_cond.utils;

public class InsufficientWalletException extends RuntimeException {
    public InsufficientWalletException(String message) {
        super(message);
    }
}
