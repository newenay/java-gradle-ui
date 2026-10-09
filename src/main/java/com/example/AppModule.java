package com.example;
import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        // Link the interface to its concrete implementation
        bind(MessageService.class).to(WeezeGuice.class);
    }
}