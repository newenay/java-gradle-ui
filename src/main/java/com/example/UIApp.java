package com.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class UIApp extends Application {
    @Override
    public void start(Stage stage) {
        TextField inputField = new TextField();
        inputField.setPromptText("Enter message for Guice...");

        Button executeBtn = new Button("Execute");

        TextField outputField = new TextField();
        // outputField.setReadOnly(true);
        outputField.setPromptText("Output will appear here...");

        TextField debugField = new TextField();
        // debugField.setReadOnly(true);
        debugField.setPromptText("Debug/Errors will appear here...");

        VBox root = new VBox(15, inputField, executeBtn, outputField, debugField);
        // root.setPadding(new Insets(20));
        root.setStyle(
                "-fx-background-color: #f5f5f5;" +
                        "-fx-font-family: 'Segoe UI';");

        executeBtn.setStyle(
                "-fx-background-color: #007bff;" +
                        "-fx-text-fill: white;" +
                        "-fx-padding: 10 20;" +
                        "-fx-border-radius: 5;" +
                        "-fx-background-radius: 5;");

        Scene scene = new Scene(root, 400, 300);

        executeBtn.setOnAction(e -> {
            String input = inputField.getText();
            try {
                Injector injector = Guice.createInjector(new AppModule());
                MessageService service = injector.getInstance(MessageService.class);
                service.sendMessage(input);
                outputField.setText("Message sent successfully: " + input);
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