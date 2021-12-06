package main.tetris.entity.block;

import main.tetris.entity.Points;

import java.awt.*;

public class BlockS extends BaseBlock {
    BlockS() {
        super(
            Color.GREEN,
            new Points(
                new Point(0, 0),
                new Point(1, 0),
                new Point(-1, -1),
                new Point(0, -1)
            )
        );
    }
}
