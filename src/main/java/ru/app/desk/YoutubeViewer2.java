package ru.app.desk;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class YoutubeViewer2 extends JFrame {
    private JTextField urlField;
    private JButton openButton;
    private JFXPanel browserPanel;

    public YoutubeViewer2() {
        setTitle("YouTube Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        JPanel urlPanel = new JPanel();
        urlPanel.setLayout(new BorderLayout());

        urlField = new JTextField("https://www.youtube.com/", 20);
        urlPanel.add(urlField, BorderLayout.CENTER);

        openButton = new JButton("Open");
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadURL(urlField.getText());
            }
        });
        urlPanel.add(openButton, BorderLayout.EAST);

        topPanel.add(urlPanel, BorderLayout.NORTH);

        browserPanel = new JFXPanel();
        topPanel.add(browserPanel, BorderLayout.CENTER);

        add(topPanel);
    }

    private void loadURL(final String url) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                WebView webView = new WebView();
                WebEngine webEngine = webView.getEngine();
                webEngine.load(url);
                browserPanel.setScene(new Scene(webView));
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                YoutubeViewer viewer = new YoutubeViewer();
                viewer.setVisible(true);
            }
        });
    }
}
