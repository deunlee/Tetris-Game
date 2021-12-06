package main.tetris.control.game;

import java.awt.Point;

import main.tetris.entity.BoardCell;
import main.tetris.entity.block.BaseBlock;

public class Board {
    private static final int WIDTH  = 10;
    private static final int HEIGHT = 20;
    private static final int DROP_X = 5;
    private static final int DROP_Y = 19;

    private BaseBlock     currentBlock; // nullable
    private BoardCell[][] boardCells;
    private int fullLines = 0;

    public Board() {
        boardCells = createEmptyBoard();
    }

    public int           getWidth()        { return WIDTH;        }
    public int           getHeight()       { return HEIGHT;       }
    public int           getFullLines()    { return fullLines;    }
    public BoardCell[][] getBoardCells()   { return boardCells;   }
    public BaseBlock     getCurrentBlock() { return currentBlock; }

    public BoardCell getBoardAt(int x, int y) {
        return boardCells[x][y];
    }

    public void rotate() {
        if (currentBlock == null) return;
        if (isFit(currentBlock.getRightRotatedPoints(), 0, 0)) {
            currentBlock.rotateRight();
        }
    }

    public void moveLeft() {
        if (currentBlock == null) return;
        if (isFit(currentBlock.getPoints(), -1, 0)) {
            currentBlock.translate( -1, 0);
        }
    }

    public void moveRight() {
        if (currentBlock == null) return;
        if (isFit(currentBlock.getPoints(), 1, 0)) {
            currentBlock.translate(1, 0);
        }
    }

    public void moveDown() {
        if (currentBlock == null) return;
        if (canCurrentBlockMoveDown()) {
            currentBlock.translate(0, -1);
            removeFullRows();
        }
    }

    public boolean canCurrentBlockMoveDown() { // 현재 블럭이 더 내려갈 수 있는지 확인
        if (currentBlock == null) return false;
        return isFit(currentBlock.getPoints(), 0, -1);
    }

    private boolean isRowFull(int y) {
        for (int x = 0; x < WIDTH; x++) {
            if (getBoardAt(x, y).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private void removeFullRows() {
        final BoardCell[][] newBoard = createEmptyBoard();
        int full = 0;
        for (int y = 0; y < HEIGHT; y++) {
            if (isRowFull(y)) {
                full++;
            } else {
                copyRow(newBoard, y, y - full);
            }
        }
        fullLines += full;
        boardCells = newBoard;
    }

    private void copyRow(BoardCell[][] to, int fromY, int toY) {
        for (int x = 0; x < WIDTH; x++) {
            to[x][toY] = boardCells[x][fromY];
        }
    }

    private BoardCell[][] createEmptyBoard() {
        final BoardCell[][] newBoard = new BoardCell[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            newBoard[x] = BoardCell.getEmptyArray(HEIGHT);
        }
        return newBoard;
    }

    private boolean isFit(final Point[] points, int dx, int dy) {
        for (final Point p : points) {
            int newX = p.x + dx;
            int newY = p.y + dy;
            if (newX < 0 || newX >= WIDTH || newY >= HEIGHT || newY < 0) {
                return false;
            }
            if (!boardCells[newX][newY].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void setCurrentBlock(final BaseBlock block) {
        if (currentBlock != null) { // 기존 블럭이 있으면 보드에 반영시킴
            for (final Point p : currentBlock.getPoints()) {
                boardCells[p.x][p.y] = new BoardCell(currentBlock.getColor());
            }
        }
        block.translate(DROP_X, DROP_Y); // 기본 위치로 설정 (맨 위 가운데)
        currentBlock = block; // 현재 블럭 변경
    }
}
