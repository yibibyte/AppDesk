package ru.app.desk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class YoutubeViewer extends JFrame {
    private JTextField urlField;
    private JButton openButton;
    private JEditorPane editorPane;

    public YoutubeViewer() {
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

        editorPane = new JEditorPane();
        editorPane.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(editorPane);
        topPanel.add(scrollPane, BorderLayout.CENTER);

        add(topPanel);
    }

    private void loadURL(String url) {
        try {
            editorPane.setPage(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
