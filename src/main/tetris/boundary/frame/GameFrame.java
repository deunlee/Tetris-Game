package main.tetris.boundary.frame;

import main.tetris.boundary.panel.BoardPanel;
import main.tetris.boundary.panel.GameStatusPanel;
import main.tetris.boundary.panel.ImagePanel;
import main.tetris.boundary.panel.PreviewPanel;
import main.tetris.control.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class GameFrame extends JFrame implements KeyListener {
    private final Game            game          = new Game();
    private final Timer           gameTimer     = new Timer();
    private final GameTimerTask   gameTimerTask = new GameTimerTask();
    private       long            lastTime      = 0;

    public GameFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tetris Game");
        setBounds(0, 0, 600, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);

        final ImagePanel imagePanel = new ImagePanel("image/logo.png");
        imagePanel.setLocation(10, 10);
        add(imagePanel);

        final GameStatusPanel gameStatusPanel = new GameStatusPanel(game);
        gameStatusPanel.setLocation(30, 120);
        add(gameStatusPanel);

        final PreviewPanel previewPanel = new PreviewPanel(game);
        previewPanel.setLocation(60, 350);
        add(previewPanel);

        final BoardPanel boardPanel = new BoardPanel(game);
        boardPanel.setLocation(340, 120);
        add(boardPanel);

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setVisible(true);
        gameTimer.schedule(gameTimerTask, 20, 20);
    }

    class GameTimerTask extends TimerTask {
        public void run() {
            if (game.isPlaying()) {
                if (!game.isPaused()) {
                    if (game.isDropping()) {
                        game.moveDown();
                    } else if (System.currentTimeMillis() - lastTime >= game.getIterationDelay()) {
                        game.moveDown();
                        lastTime = System.currentTimeMillis();
                    }
                }
            }
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!game.isPlaying() || game.isPaused()) return; // 게임 플레이중이 아닌 경우 무시
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                game.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                game.moveRight();
                break;
            case KeyEvent.VK_UP:
                game.rotate();
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_SPACE:
                game.drop();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_S:
                game.startGame();
                break;
            case KeyEvent.VK_P:
                if (game.isPlaying()) {
                    game.pauseGame();
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }
}
