package main.tetris.boundary.frame;

import main.tetris.control.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginFrame extends BaseFrame {
    private final JTextField txtName;
    private final JTextField txtPass;

    public LoginFrame() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("Login");
        setBounds(0, 0, 600, 450);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                new MainFrame();
                dispose();
            }
        });

        addLabel("L O G I N", 180, 30, 50, Color.WHITE);
        addLabel("Name : ",     90, 150, 25, Color.WHITE);
        addLabel("Password : ", 90, 220, 25, Color.WHITE);
        txtName = addTextField    (220, 150, 290, 25);
        txtPass = addPasswordField(220, 220, 290, 25);
        addButton("Login", 120, 300, 360, 50);

        setVisible(true);
    }

    private void login() {
        final String name = txtName.getText();
        final String pass = txtPass.getText();
        if (name == null || name.length() < 4) {
            JOptionPane.showMessageDialog(null, "The name must be at least 4 characters long.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (pass == null || pass.length() < 4) {
            JOptionPane.showMessageDialog(null, "The password must be at least 4 characters long.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!UserManager.login(name, pass)) {
            JOptionPane.showMessageDialog(null, "The user does not exist or the password is incorrect.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        new MainFrame();
        dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final JButton button = (JButton)e.getSource();
        switch (button.getText()) {
            case "Login":
                login();
                break;
        }
    }
}
