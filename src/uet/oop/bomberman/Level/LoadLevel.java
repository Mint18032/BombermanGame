package uet.oop.bomberman.Level;

import uet.oop.bomberman.GameBoard;

public abstract class LoadLevel {

    protected int width = 20, height = 20;
    protected int level;
    protected GameBoard gameboard;

    public LoadLevel(GameBoard gameboard, int level) {
        this.gameboard = gameboard;
        loadLevel(level);
    }

    public abstract void loadLevel(int level);

    public abstract void createEntities();

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLevel() {
        return level;
    }
}
