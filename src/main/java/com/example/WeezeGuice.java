package com.example;

public class WeezeGuice implements MessageService {
    @Override
    public void sendMessage(String msg) {
        System.out.println("Sending message: " + msg);
    }
}

