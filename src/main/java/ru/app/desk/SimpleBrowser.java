package ru.app.desk;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.*;

public class SimpleBrowser extends JFrame {
    private JTextField urlField;
    private JButton goButton;
    private JEditorPane browserPane;

    public SimpleBrowser() {
        setTitle("Simple Browser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        urlField = new JTextField("https://www.google.com", 20);
        goButton = new JButton("Go");
        goButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadURL(urlField.getText());
            }
        });

        topPanel.add(urlField, BorderLayout.CENTER);
        topPanel.add(goButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        browserPane = new JEditorPane();
        browserPane.setEditable(false);
        browserPane.setContentType("text/html");
        browserPane.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    loadURL(e.getURL().toString());
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(browserPane);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadURL(String url) {
        try {
            browserPane.setPage(url);
            urlField.setText(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SimpleBrowser browser = new SimpleBrowser();
                browser.setVisible(true);
            }
        });
    }
}
