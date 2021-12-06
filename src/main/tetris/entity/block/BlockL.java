package main.tetris.entity.block;

import main.tetris.entity.Points;

import java.awt.*;

public class BlockL extends BaseBlock {
    BlockL() {
        super(
            Color.CYAN,
            new Points(
                new Point(-1, 0),
                new Point(0, 0),
                new Point(1, 0),
                new Point(-1, -1)
            )
        );
    }
}
