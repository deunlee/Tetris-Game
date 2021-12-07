package main.tetris.entity.block;

import java.awt.*;

import main.tetris.entity.Points;

public class BaseBlock {
    private final Color  color;
    private final Point  center;
    private final Points points;

    BaseBlock(final Color color, final Points points) {
        this.color  = color;
        this.points = points;
        this.center = new Point(0, 0);
    }

    public Color getColor() {
        return color;
    }

    public Point[] getPoints() {
        return points.copy().translate(center).getPoints();
    }

    public Point[] getRightRotatedPoints() {
        return points.copy().rotateRight().translate(center).getPoints();
    }

    public void translate(int dx, int dy) {
        center.translate(dx, dy);
    }

    public void rotateLeft()  { points.rotateLeft();  }
    public void rotateRight() { points.rotateRight(); }
}
