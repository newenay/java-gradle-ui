package com.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UIApp extends Application {
    public void start(Stage stage) {
        TextField inputField = new TextField();
        TextField outputField = new TextField();
        TextField debugField = new TextField();
        Button executeBtn = new Button("Execute");
        Label outputLabel = new Label("Foo");

        VBox root = new VBox(10, inputField, executeBtn, outputField, debugField, outputLabel);
        Scene scene = new Scene(root, 400, 300);

        executeBtn.setOnAction(e -> {
            String input = inputField.getText();
            try {
                Injector injector = Guice.createInjector(new AppModule());
                MessageService service = injector.getInstance(MessageService.class);
                service.sendMessage(input);
                outputField.setText("Message sent: " + input);
                debugField.setText("SUCCESS");
            } catch (Exception ex) {
                outputField.setText("Error occurred");
                debugField.setText(ex.getMessage());
            }
        });

        stage.setScene(scene);
        stage.setTitle("Guice UI");
        stage.show();
    }
}


