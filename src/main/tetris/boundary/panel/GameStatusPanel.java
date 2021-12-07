package main.tetris.boundary.panel;

import main.tetris.control.game.Game;

import javax.swing.*;
import java.awt.*;

public class GameStatusPanel extends JPanel {
    private final Game  game;
    private final Font  font;
    private final Font  fontLarge;
    private final Font  fontSmall;

    public GameStatusPanel(final Game game) {
        this.game      = game;
        this.font      = new Font("Consolas", Font.BOLD, 22);
        this.fontLarge = new Font("Consolas", Font.BOLD, 28);
        this.fontSmall = new Font("Consolas", Font.PLAIN, 18);

        setSize(new Dimension(300, 240));
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(font);
        if (!game.isPlaying() && !game.isGameOver()) {
            g.setColor(Color.WHITE);
            g.drawString("Press 'S' Key to START!", 0, 20);

            g.setColor(Color.YELLOW);
            switch (game.getDifficulty()) {
                case 0:
                    g.drawString("Difficulty : Easy", 0, 70);
                    break;
                case 1:
                    g.drawString("Difficulty : Normal", 0, 70);
                    break;
                case 2:
                default:
                    g.drawString("Difficulty : Hard", 0, 70);
            }

            g.setFont(fontSmall);
            g.setColor(Color.GRAY);
            g.drawString("Move   : Left/Right", 0, 120);
            g.drawString("Rotate : Up",         0, 150);
            g.drawString("Drop   : Space/Down", 0, 180);
            g.drawString("Difficulty : Tab",    0, 210);
            g.drawString("Ranking    : F1",     0, 240);
            return;
        }

        g.setColor(Color.YELLOW);
        g.drawString(String.format("Your Level  : %d", game.getLevel()), 0, 20);
        g.drawString(String.format("Full Lines  : %d", game.getFullLines()), 0, 50);
        g.drawString(String.format("Total Score : %d", game.getTotalScore()), 0, 80);

        g.setFont(fontLarge);
        if (game.isPlaying()) {
            if (game.isPaused()) {
                g.setColor(Color.GRAY);
                g.drawString("GAME PAUSED", 30, 150);
            }
        } else if (game.isGameOver()) {
            g.setColor(Color.MAGENTA);
            g.drawString("GAME OVER", 30, 150);
            g.setFont(font);
            g.drawString("Press 'R' to Reset!", 0, 200);
        }
    }
}
