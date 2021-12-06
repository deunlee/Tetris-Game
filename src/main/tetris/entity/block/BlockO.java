package main.tetris.entity.block;

import main.tetris.entity.Points;

import java.awt.*;

public class BlockO extends BaseBlock {
    BlockO() {
        super(
            Color.BLUE,
            new Points(
                new Point(-1, 0),
                new Point(0, 0),
                new Point(-1, -1),
                new Point(0, -1)
            )
        );
    }
}
