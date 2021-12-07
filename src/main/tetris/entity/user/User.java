package main.tetris.entity.user;

public class User {
    private String id;
    private String password;
    private String name;
    private int    bestScore;

    public User(String id, String password, String name, int bestScore) {
        this.id        = id;
        this.password  = password;
        this.name      = name;
        this.bestScore = bestScore;
    }

    public String getId()     { return id;        }
    public String getName()   { return name;      }
    public int getBestScore() { return bestScore; }

    public void setBestScore(int score) {
        bestScore = score;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}
