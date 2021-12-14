package main.tetris.boundary.frame;

import main.tetris.control.DatabaseManager;
import main.tetris.entity.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;

public class RankingFrame extends BaseFrame {
    public RankingFrame() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("Ranking");
        setBounds(0, 0, 600, 480);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                dispose();
            }
        });

        addLabel("R A N K I N G", 130,  30, 50, Color.WHITE);
        addLabel("No.",           120, 120, 20, Color.WHITE);
        addLabel("Name",          200, 120, 20, Color.WHITE);
        addLabel("Best Score",    350, 120, 20, Color.WHITE);
        addButton("Back", 120, 360, 360, 50);

        final ArrayList<User> users = DatabaseManager.getInstance().getUsers();
        Collections.sort(users);
        for (int i = 0; i < 5; i++) {
            if (i < users.size()) {
                final User u = users.get(i);
                addLabel(Integer.toString(i+1),              130, 160 + 35 * i, 20, Color.WHITE);
                addLabel(u.getName(),                        200, 160 + 35 * i, 20, Color.WHITE);
                addLabel(Integer.toString(u.getBestScore()), 370, 160 + 35 * i, 20, Color.WHITE);
            } else {
                addLabel(Integer.toString(i+1),              130, 160 + 35 * i, 20, Color.WHITE);
                addLabel("(no data)",                        200, 160 + 35 * i, 20, Color.WHITE);
                addLabel("---",                              370, 160 + 35 * i, 20, Color.WHITE);
            }
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final JButton button = (JButton)e.getSource();
        switch (button.getText()) {
            case "Back":
                dispose();
                break;
        }
    }
}
