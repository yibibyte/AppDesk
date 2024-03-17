package ru.app.desk.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import java.net.URL;

public class SimpleBrowserJavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        TextField urlField = new TextField("https://www.google.com");
        Button goButton = new Button("Go");
        Button backButton = new Button("Back");
        Button forwardButton = new Button("Forward");

        HBox buttonBox = new HBox(backButton, forwardButton);
        buttonBox.setSpacing(5);

        HBox urlBox = new HBox(urlField, goButton);
        urlBox.setSpacing(5);

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        WebHistory history = webEngine.getHistory();

        goButton.setOnAction(e -> {
            String url = urlField.getText();
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://" + url;
            }
            webEngine.load(url);
        });

        backButton.setOnAction(e -> {
            if (history.getCurrentIndex() > 0) {
                history.go(-1);
            }
        });

        forwardButton.setOnAction(e -> {
            if (history.getCurrentIndex() < history.getEntries().size() - 1) {
                history.go(1);
            }
        });

        webView.getEngine().setCreatePopupHandler(config -> {
            primaryStage.show();
            return null;
        });

        root.setTop(new VBox(urlBox, buttonBox));
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
