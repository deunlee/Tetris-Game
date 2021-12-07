package main.tetris.boundary.panel;

import main.tetris.control.game.Game;
import main.tetris.entity.block.BaseBlock;

import javax.swing.*;
import java.awt.*;

import static main.tetris.boundary.panel.BoardPanel.BLOCK_HEIGHT;
import static main.tetris.boundary.panel.BoardPanel.BLOCK_WIDTH;

public class PreviewPanel extends JPanel {
    private final Game game;
    private final Font font;

    public PreviewPanel(final Game game) {
        this.game = game;
        this.font = new Font("Consolas", Font.BOLD, 22);

        setSize(new Dimension(140, 140));
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.setColor(Color.GRAY);
//        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        if (game.isPlaying()) {
            g.setColor(Color.GRAY);
            g.setFont(font);
            g.drawString("Next:", 10, 30);
            for (final Point p : game.getNextBlock().getPoints()) {
                drawBlock(g, p.x, p.y, game.getNextBlock().getColor());
            }
        }
    }

    private void drawBlock(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(
            70 + x * BLOCK_WIDTH,
            (3 - y) * BLOCK_HEIGHT,
            BLOCK_WIDTH,
            BLOCK_HEIGHT
        );
    }
}
