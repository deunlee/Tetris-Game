package main.tetris.boundary.panel;

import main.tetris.control.game.Game;
import main.tetris.entity.BoardCell;
import main.tetris.entity.block.BaseBlock;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    public static final int BLOCK_WIDTH  = 20;
    public static final int BLOCK_HEIGHT = 20;
    public static final int BORDER_WIDTH = 2;

    private final Game game;

    public BoardPanel(final Game game) {
        this.game = game;
        setSize(new Dimension(
            (game.getBoard().getWidth()  * BLOCK_WIDTH)  + (BORDER_WIDTH * 2),
            (game.getBoard().getHeight() * BLOCK_HEIGHT) + (BORDER_WIDTH * 2)
        ));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawEmptyBoard(g);
        drawBlocks(g);       // first
        drawCurrentBlock(g); // second
    }

    private void drawEmptyBoard(final Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void drawCurrentBlock(final Graphics g) {
        final BaseBlock currentBlock = game.getBoard().getCurrentBlock();
        if (currentBlock == null) return;
        for (final Point p : currentBlock.getPoints()) {
            drawBlock(g, p.x, p.y, currentBlock.getColor());
        }
    }

    private void drawBlocks(final Graphics g) {
        final BoardCell[][] cells = game.getBoard().getBoardCells();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                drawBlock(g, i, j, cells[i][j].getColor());
            }
        }
    }

    private void drawBlock(final Graphics g, int x, int y, final Color color) {
        g.setColor(color);
        g.fillRect(
            BORDER_WIDTH + x * BLOCK_WIDTH,
            BORDER_WIDTH + (19 - y) * 20,
            BLOCK_WIDTH,
            BLOCK_HEIGHT
        );
    }
}
