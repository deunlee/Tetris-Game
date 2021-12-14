package main.tetris.boundary.frame;

import main.tetris.boundary.panel.ImagePanel;
import main.tetris.control.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends BaseFrame {
    public MainFrame() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tetris Game");
        setBounds(0, 0, 600, 520);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);

        final ImagePanel imagePanel = new ImagePanel("image/logo.png");
        imagePanel.setLocation(10, 10);
        add(imagePanel);

        final JButton btnPlay     = addButton("Play Game", 120, 130, 360, 50);
        final JButton btnRanking  = addButton("Ranking",   120, 200, 360, 50);
        final JButton btnLogin    = addButton("Login",     120, 300, 170, 50);
        final JButton btnRegister = addButton("Register",  310, 300, 170, 50);
        final JButton btnLogout   = addButton("Logout",    120, 300, 360, 50);
        final JButton btnExit     = addButton("Exit",      120, 370, 360, 50);

        if (UserManager.getCurrentUser() == null) {
            btnPlay.setEnabled(false);
            btnLogout.setVisible(false);
        } else {
            btnLogin.setVisible(false);
            btnRegister.setVisible(false);
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final JButton button = (JButton)e.getSource();
        switch (button.getText()) {
            case "Play Game":
                new GameFrame();
                break;
            case "Ranking":
                new RankingFrame();
                break;
            case "Login":
                new LoginFrame();
                break;
            case "Register":
                new RegisterFrame();
                break;
            case "Logout":
                UserManager.logout();
                new MainFrame();
                break;
            case "Exit":
                break;
        }
        dispose();
    }
}
