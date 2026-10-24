package com.example;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class UIApp extends Application {
    
    // **** Generic MessageSvc ****
    /* public static class MessageService {
        @Inject
        public MessageService() {}
        public void sendMessage(String msg) {
            System.out.println("Sending message: " + msg);
        }
    } */
    
    public static class Product {
        private final String name;
        private final double price;
        private final int id;
        
        @Inject
        public Product(String name, double price, int id) {
            this.name = name;
            this.price = price;
            this.id = id;
        }
        public String getName() { return name; }
        public double getPrice() { return price; }
        public int getId() { return id; }
        @Override
        public String toString() { return name + " ($" + price + ")"; }
    }
    
    public static class Cart {
        private final java.util.List<Product> items = new java.util.ArrayList<>();
        
        public void addProduct(Product product) {
            items.add(product);
            System.out.println("Product added to cart: " + product);
        }
        
        public void listProducts() {
            System.out.println("Cart contents: " + items);
        }
    }
    
    public static class ProductDAO {
        private final Cart cart;
        
        public ProductDAO() {
            this.cart = new Cart();
        }
        
        public Product getProduct(String name) {
            return new Product(name, 9.99, 1);
        }
    }
    
    @Override
    public void start(Stage stage) {
        TextField inputField = new TextField();
        inputField.setPromptText("Enter product name for cart...");
        inputField.getStyleClass().add("input-field");

        Button executeBtn = new Button("Add to Cart");
        executeBtn.getStyleClass().add("execute-btn");

        TextField outputField = new TextField();
        // outputField.setReadOnly(true);
        outputField.setPromptText("Output will appear here...");
        outputField.getStyleClass().add("readonly-text-field");

        TextField debugField = new TextField();
        // debugField.setReadOnly(true);
        debugField.setPromptText("Debug/Errors will appear here...");
        debugField.getStyleClass().add("readonly-text-field");

        VBox root = new VBox(15, inputField, executeBtn, outputField, debugField);
        root.getStyleClass().add("main-root");

        Scene scene = new Scene(root, 400, 300);
        scene.getStylesheets().add(UIApp.class.getResource("/styles/main.css").toExternalForm());

        executeBtn.setOnAction(e -> {
            String input = inputField.getText();
            try {
                Injector injector = Guice.createInjector(new com.google.inject.AbstractModule() {
                    @Override
                    protected void configure() {
                        bind(ProductDAO.class).toInstance(new ProductDAO());
                        bind(Cart.class).toInstance(new Cart());
                        // bind(MessageService.class).toInstance(new MessageService());
                    }
                });
                
                ProductDAO dao = injector.getInstance(ProductDAO.class);
                Product product = dao.getProduct(input);
                Cart cart = injector.getInstance(Cart.class);
                cart.addProduct(product);
                outputField.setText("Product added to cart: " + input);
                debugField.setText("Status: OK");
            } catch (Exception ex) {
                outputField.setText("Error occurred");
                debugField.setText("Error: " + ex.getMessage());
            }
        });

        stage.setTitle("Guice UI Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}