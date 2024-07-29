package server.server;

import javax.swing.*;
import java.awt.*;

public class ServerGUI extends JFrame implements ServerView {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private JButton btnStart, btnStop;
    private JTextArea log;
    private ServerController serverController = new ServerController(this);

    public ServerGUI() {
        setting();
        createPanel();

        setVisible(true);
    }

    private void setting() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);
    }

    public void createPanel() {
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");


        btnStop = new JButton("Stop");

        btnStart.addActionListener(e -> serverController.start());
        btnStop.addActionListener(e -> serverController.stop());

        panel.add(btnStart);
        panel.add(btnStop);

        return panel;
    }

    @Override
    public void showMessage(String text) {
        log.append(text);
    }

    @Override
    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }
}
