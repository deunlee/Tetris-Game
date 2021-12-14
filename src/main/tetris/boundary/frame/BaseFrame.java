package main.tetris.boundary.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class BaseFrame extends JFrame implements ActionListener {
    protected JLabel addLabel(final String text, int x, int y, int size, final Color color) {
        final JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, size));
        final Dimension d = label.getPreferredSize();
        label.setBounds(x, y, d.width, d.height);
        label.setForeground(color);
        add(label);
        return label;
    }

    protected JButton addButton(final String name, int x, int y, int width, int height) {
        final JButton button = new JButton(name);
        button.setBounds(x, y, width, height);
        button.setBackground(Color.ORANGE);
        button.setOpaque(true);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.addActionListener(this);
        add(button);
        return button;
    }

    protected JTextField addTextField(int x, int y, int width, int fontSize) {
        final JTextField text = new JTextField();
        text.setFont(new Font("Arial", Font.PLAIN, fontSize));
        final Dimension d = text.getPreferredSize();
        text.setBounds(x, y, width, d.height);
        add(text);
        return text;
    }

    protected JPasswordField addPasswordField(int x, int y, int width, int fontSize) {
        final JPasswordField text = new JPasswordField();
        text.setFont(new Font("Arial", Font.PLAIN, fontSize));
        final Dimension d = text.getPreferredSize();
        text.setBounds(x, y, width, d.height);
        add(text);
        return text;
    }
}
