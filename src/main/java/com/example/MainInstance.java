package com.example;
import com.google.inject.Inject;

public class MainInstance {
    private final MessageService service;

    // Guice will automatically pass the configured MessageService here
    @Inject
    public MainInstance(MessageService service) {
        this.service = service;
    }

    public void run(String msg) {
        this.service.sendMessage(msg);
    }
}
