package main.tetris.boundary.frame;

import main.tetris.control.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RegisterFrame extends BaseFrame {
    private final JTextField txtName;
    private final JTextField txtPass;

    public RegisterFrame() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("Register");
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

        addLabel("R E G I S T E R", 120, 30, 50, Color.WHITE);
        addLabel("Name : ",     90, 150, 25, Color.WHITE);
        addLabel("Password : ", 90, 220, 25, Color.WHITE);
        txtName = addTextField    (220, 150, 290, 25);
        txtPass = addPasswordField(220, 220, 290, 25);
        addButton("Register", 120, 300, 360, 50);

        setVisible(true);
    }

    private void register() {
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
        if (UserManager.isUserExists(name)) {
            JOptionPane.showMessageDialog(null, "The name already exists.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!UserManager.register(name, pass)) {
            JOptionPane.showMessageDialog(null, "Failed to register.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        new MainFrame();
        dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final JButton button = (JButton)e.getSource();
        switch (button.getText()) {
            case "Register":
                register();
                break;
        }
    }
}
