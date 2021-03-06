package main.tetris.entity.user;

import java.io.Serializable;

public class User implements Serializable, Comparable<User> {
    private final String name;
    private final String password;
    private int          bestScore;

    public User(final String name, final String password, int bestScore) {
        this.name      = name;
        this.password  = password;
        this.bestScore = bestScore;
    }

    public String getName()      { return name;      }
    public int    getBestScore() { return bestScore; }

    public void setBestScore(int score) {
        bestScore = score;
    }

    public boolean checkPassword(final String password) {
        return this.password.equals(password);
    }

    @Override
    public int compareTo(User o) {
        return o.getBestScore() - getBestScore();
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", score=" + bestScore + "]";
    }
}
