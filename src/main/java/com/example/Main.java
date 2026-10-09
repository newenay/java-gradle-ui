// src/main/java/com/example/Main.java
package com.example;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {
    public static void main(String[] args) {
        // Create the Guice context container
        Injector injector = Guice.createInjector(new AppModule());
        
        // Retrieve a fully constructed instance of your application
        MainInstance app = injector.getInstance(MainInstance.class);
        
        app.run("Hello, Google Guice!");
        
        System.out.println("Hello, World!");
    }
}
