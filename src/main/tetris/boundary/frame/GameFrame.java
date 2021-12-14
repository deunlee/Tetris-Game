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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

public class GameFrame extends JFrame implements KeyListener {
    private final Game            game          = new Game();
    private final Timer           gameTimer     = new Timer();
    private final GameTimerTask   gameTimerTask = new GameTimerTask();
    private       long            lastTime      = 0;

    public GameFrame() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("Tetris Game");
        setBounds(0, 0, 600, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                new MainFrame();
                close();
            }
        });

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
            // repaint() 호출시 하위 컴포넌트(ex: 패널)들이 다시 그려짐
            // (하위 컴포넌트의 paintComponent() 가 호출됨)
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
                if (game.isGameOver()) {
                    game.resetGame();
                } else if (!game.isPlaying()) {
                    game.startGame();
                }
                break;
            case KeyEvent.VK_P:
                if (game.isPlaying()) game.pauseGame();
                break;
            case KeyEvent.VK_TAB:
                if (!game.isPlaying()) game.setDifficulty((game.getDifficulty() + 1) % 3);
                break;
            case KeyEvent.VK_R:
                if (!game.isPlaying() && !game.isGameOver()) {
                    new RankingFrame();
                }
                break;
            case KeyEvent.VK_Q:
                close();
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    private void close() {
        gameTimer.cancel();
        dispose();
    }
}
