package uet.oop.bomberman.Game.entities.Characters;

import uet.oop.bomberman.Game.entities.Animation;
import uet.oop.bomberman.GameBoard;
import uet.oop.bomberman.graphics.Screen;

public abstract class Characters extends Animation {
    protected int direction = -1;
    protected boolean alive = true;
    protected boolean moving = false;
    protected GameBoard gameBoard;
    public int timeAfter = 40;

    public Characters(int x, int y, GameBoard board) {
        this.x = x;
        this.y = y;
        gameBoard = board;
    }

    //Calculate Move
    public abstract void calcMove();
    //Move
    public abstract boolean canMove(double _x, double _y);
    public abstract void move(double xa, double ya) ;
    //Kill
    public abstract void canKill();
    public abstract void afterKill();


    @Override
    public void update() {}

    @Override
    public void render(Screen screen) {}

}
