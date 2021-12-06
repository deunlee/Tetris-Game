package main.tetris.entity.block;

import main.tetris.entity.Points;

import java.awt.*;

public class BlockI extends BaseBlock {
    BlockI() {
        super(
            Color.RED,
            new Points(
                new Point(-2, 0),
                new Point(-1, 0),
                new Point(0, 0),
                new Point(1, 0)
            )
        );
    }
}
