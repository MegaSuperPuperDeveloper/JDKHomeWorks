package org.example.HomeWork1.client;

import org.example.HomeWork1.server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class ClientWindow extends JFrame {

    private boolean login = false;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static final int POSX = 300;
    private static final int POSY = 500;
    private String lastString;
    private String getLastString;
    private Path path = Path.of("src/main/java/org/example/HomeWork1/logs.txt");

    JPanel panelLogin = new JPanel(new GridLayout(2, 3));
    JTextField tIp = new JTextField("127.0.0.1");
    JTextField tPort = new JTextField("8189");
    JTextField tName = new JTextField("ivan_igorevich");
    JPasswordField tPassword = new JPasswordField();
    JButton btnLogin = new JButton("Login");

    JTextArea chat = new JTextArea();
    JPanel panelChat = new JPanel(new BorderLayout());
    JTextField tText = new JTextField();
    JButton btnSend = new JButton("Send");

    JList<String> jListContacts = new JList<>();
    DefaultListModel<String> model = new DefaultListModel<>();
    JSplitPane splitPane = new JSplitPane();

    public ClientWindow(ServerWindow server) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(POSX, POSY);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Client");
        setResizable(true);
        chat.setEditable(false);

        model.addElement("Nikolay");
        model.addElement("Misha");
        model.addElement("Dima");
        model.addElement("Nikita");
        jListContacts.setModel(model);
        panelChat.add(new JScrollPane(jListContacts), BorderLayout.EAST);

        

        panelLogin.add(tIp);
        panelLogin.add(tPort);
        panelLogin.add(tName);
        panelLogin.add(tPassword);
        panelLogin.add(btnLogin);
        add(panelLogin, BorderLayout.NORTH);

        btnSend.setMargin(new Insets(2, 10, 2, 10));
        panelChat.add(tText, BorderLayout.CENTER);
        panelChat.add(btnSend, BorderLayout.EAST);
        add(panelChat, BorderLayout.SOUTH);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (server.getStatus() && isNullLogin() && isCorrectIP()) {
                    panelLogin.setVisible(false);
                    chat.append("Вы успешно авторизовались!\n");
                    login = true;
                    add(new JScrollPane(jListContacts), BorderLayout.WEST);
                    if (Files.exists(path)) {
                        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                chat.append(line + "\n");
                            }
                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    add(new JScrollPane(chat));
                }
            }
        });

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage(server);
            }
        });



        tText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage(server);
                }
            }
        });

        setVisible(true);
    }

    private boolean isNullLogin() {
        return !Objects.equals(tName.getText(), "") &&
                !Objects.equals(tIp.getText(), "") &&
                !Objects.equals(tPassword.getText(), "") &&
                !Objects.equals(tPort.getText(), "");
    }

    private boolean isCorrectIP() {
        String[] ip = tIp.getText().split("\\.");
        int num;
        if (ip.length != 4)
            return false;
        try {
            for (String s : ip) {
                num = Integer.parseInt(s);
                if (num < 0 || num > 255)
                    return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Отправка сообщения на сервер, которое сервер в будущем отправит другому клиенту + записывает сообщение в логи
     * @param server - сам сервер
     */
    private void sendMessage(ServerWindow server) {
        if (login && server.getStatus() && !Objects.equals(tText.getText(), "")) {
            lastString = tName.getText() + ": " + tText.getText() + "\n";
            try(FileWriter file = new FileWriter("src/main/java/org/example/HomeWork1/logs.txt", true)) {
                file.write(lastString);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            chat.append(lastString);
            tText.setText("");
            server.setLastString(lastString, this);
        }
    }

    /**
     * Получение сообщения от сервера
     * @param getLastString - само сообщение
     */
    public void getMessage(String getLastString) {
        this.getLastString = getLastString;
        chat.append(getLastString);
    }

    public String getName() {
        return tName.getText();
    }

}
