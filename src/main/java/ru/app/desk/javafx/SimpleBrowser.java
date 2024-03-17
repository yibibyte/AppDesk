package ru.app.desk.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class SimpleBrowser extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        TextField urlField = new TextField("https://www.google.com");
        Button goButton = new Button("Go");
        HBox urlBox = new HBox(urlField, goButton);
        urlBox.setSpacing(5);

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        goButton.setOnAction(e -> {
            String url = urlField.getText();
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://" + url;
            }
            webEngine.load(url);
        });

        webView.getEngine().setCreatePopupHandler(config -> {
            primaryStage.show();
            return null;
        });

        root.setTop(urlBox);
        root.setCenter(webView);

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Simple Browser");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
