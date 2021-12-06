package main.tetris.entity.block;

import java.util.Random;

public class BlockFactory {
    private static final Random random = new Random();

    public static BaseBlock getRandomBlock() {
        switch (random.nextInt(7)) {
            case 0:
                return new BlockI();
            case 1:
                return new BlockJ();
            case 2:
                return new BlockL();
            case 3:
                return new BlockO();
            case 4:
                return new BlockS();
            case 5:
                return new BlockT();
            case 6:
            default:
                return new BlockZ();
        }
    }
}
