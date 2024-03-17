package ru.app.desk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class OpenYoutubeViewer extends JFrame {
    private JTextField urlField;
    private JButton openButton;

    public OpenYoutubeViewer() {
        setTitle("YouTube Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 100);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        urlField = new JTextField("https://www.youtube.com/", 20);
        panel.add(urlField);

        openButton = new JButton("Open");
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openURL(urlField.getText());
            }
        });
        panel.add(openButton);

        add(panel);
    }

    private void openURL(String url) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                try {
                    desktop.browse(new URI(url));
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                OpenYoutubeViewer viewer = new OpenYoutubeViewer();
                viewer.setVisible(true);
            }
        });
    }
}
