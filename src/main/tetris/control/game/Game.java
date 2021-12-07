package main.tetris.control.game;

import main.tetris.entity.block.BaseBlock;
import main.tetris.entity.block.BlockFactory;

public class Game {
    private Board     board;
    private BaseBlock nextBlock;

    // 게임 상태
    // stopped -> playing -> game over
    //            playing -> paused   -> playing
    //            playing -> dropping -> playing
    private boolean isPlaying  = false;
    private boolean isPaused   = false;
    private boolean isDropping = false;
    private boolean isGameOver = false;

    private int difficulty = 1;
    private int distance   = 0; // 현재 블럭이 내려간 거리
    private int totalScore = 0;

    public Game() {
        resetGame();
    }

    public void resetGame() {
        difficulty = 1;
        distance   = 0;
        totalScore = 0;
        board      = new Board();
        nextBlock  = BlockFactory.getRandomBlock();
        isPlaying  = false;
        isPaused   = false;
        isDropping = false;
        isGameOver = false;
    }

    public void startGame() {
        resetGame();
        board.setCurrentBlock(BlockFactory.getRandomBlock());
        isPlaying = true;
    }

    public void pauseGame() {
        isPaused = !isPaused;
    }

    public Board         getBoard()      { return board;                }
    public BaseBlock     getNextBlock()  { return nextBlock;            }
    public int           getFullLines()  { return board.getFullLines(); }
    public int           getDifficulty() { return difficulty;           }
    public int           getTotalScore() { return totalScore;           }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isPlaying()  { return isPlaying;  }
    public boolean isPaused()   { return isPaused;   }
    public boolean isDropping() { return isDropping; }
    public boolean isGameOver() { return isGameOver; }

    public long getIterationDelay() {
        return (long)(((11 - getLevel()) * 0.05) * 1000 + 100 - difficulty * 100);
    }

    public int getScore() {
        return getLevel() * 3 + (board.getHeight() + 1 - distance);
    }

    public int getLevel() {
        if ((board.getFullLines() >= 1) && (board.getFullLines() <= 90)) {
            return 1 + ((board.getFullLines() - 1) / 10);
        } else if (board.getFullLines() >= 91) {
            return 10;
        } else {
            return 1;
        }
    }



    public void rotate()    { board.rotate();    }
    public void drop()      { isDropping = true; }
    public void moveLeft()  { board.moveLeft();  }
    public void moveRight() { board.moveRight(); }

    public void moveDown() {
        if (board.canCurrentBlockMoveDown()) { // 현재 블럭이 더 내려갈 수 있으면
            board.moveDown(); // 한 칸 내리기
            distance++; // distance는 현재 블럭이 내려간 거리
        } else {
            if (distance == 0) { // 현재 블럭이 내려간 거리가 0이고, 더 내려갈 수 없으면
                isPlaying  = false;
                isPaused   = false;
                isGameOver = true; // 게임 오버
            } else { // 현재 블럭은 더 내려갈 수 없지만, 빈 칸이 남았다면
                isDropping = false;
                board.setCurrentBlock(nextBlock); // 새로운 블럭 추가
                nextBlock = BlockFactory.getRandomBlock();
                totalScore += getScore();
                distance = 0; // 새로운 블럭이 내려간 거리 0으로 초기화
            }
        }
    }
}
