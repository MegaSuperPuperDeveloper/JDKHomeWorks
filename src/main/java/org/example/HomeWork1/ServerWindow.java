package org.example.HomeWork1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class ServerWindow extends JFrame {
    private boolean isServerWorking = false;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static final int POSX = 1000;
    private static final int POSY = 500;
    private String lastString;

    JButton btnStart = new JButton("Start");
    JButton btnStop = new JButton("Stop");
    JPanel panel = new JPanel(new GridLayout(1, 2));
    ClientWindow client1 = new ClientWindow(this);
    ClientWindow client2 = new ClientWindow(this);
    JTextArea logs = new JTextArea("Сервер выключен\n");

    public ServerWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(POSX, POSY);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Server");
        setResizable(false);

        logs.setEditable(false);
        add(new JScrollPane(logs));
        add(logs);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    logs.append("Сервер уже работает\n");
                } else {
                    isServerWorking = true;
                    logs.append("Сервер был запущен\n");
                }
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isServerWorking) {
                    logs.append("Сервер уже выключен\n");
                } else {
                    isServerWorking = false;
                    logs.append("Сервер был выключен\n");
                }
            }
        });


        panel.add(btnStart);
        panel.add(btnStop);
        add(panel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public boolean getStatus() {
        return isServerWorking;
    }

    /**
     * Сервер получает сообщение от клиента и отправляет его другому клиенту
     * @param lastString - сам текст
     * @param client - клиент от которого получили сообщение для координации
     */
    public void setLastString(String lastString, ClientWindow client) {
        this.lastString = lastString;
        logs.append(lastString);
        if (client == client1) {
            client2.getMessage(lastString);
        } else if (client == client2) {
            client1.getMessage(lastString);
        }
    }

}