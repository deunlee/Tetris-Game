package main.tetris.entity;

import java.awt.Point;

public class Points {
    private final Point[] points;

    public Points(final Point... points) {
        this.points = points;
    }

    public Point[] getPoints() {
        return points;
    }

    public Points translate(int dx, int dy) { // return this;
        for (final Point p : points) {
            p.translate(dx, dy);
        }
        return this;
    }
    public Points translate(final Point p) { // return this;
        return translate(p.x, p.y);
    }

    public Points rotateLeft()  { return rotatePoints(1, -1); } // return this;
    public Points rotateRight() { return rotatePoints(-1, 1); } // return this;

    private Points rotatePoints(int x, int y) { // return this;
        for (final Point p : points) {
            p.move(x * p.y, y * p.x);
        }
        return this;
    }

    public Points copy() {
        final Point[] newPoints = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            newPoints[i] = new Point(points[i].x, points[i].y);
        }
        return new Points(newPoints);
    }
}
