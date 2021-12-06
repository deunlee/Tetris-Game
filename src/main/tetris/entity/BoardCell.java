package main.tetris.entity;

import java.awt.Color;
import java.util.Arrays;

public class BoardCell {
    private final Color color;

    public BoardCell() {
        color = Color.BLACK;
    }

    public BoardCell(Color color) {
        this.color = color;
    }

    public boolean isEmpty() {
        return color == Color.BLACK;
    }

    public Color getColor() {
        return color;
    }

    public static BoardCell[] getEmptyArray(int size) {
        BoardCell[] cells = new BoardCell[size];
        Arrays.fill(cells, new BoardCell());
        return cells;
    }
}
