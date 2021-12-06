package main.tetris.boundary.panel;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    private Image image;

    public ImagePanel(final Image image) {
        this.image = image;
        setSize(new Dimension(image.getWidth(null), image.getHeight(null)));
        setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
        setLayout(null);
    }

    public ImagePanel(final String path) {
        this(new ImageIcon(path).getImage());
    }

    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
}

