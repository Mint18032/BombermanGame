package uet.oop.bomberman.Game.entities.Characters.Enemy;

import uet.oop.bomberman.Game.entities.Characters.Characters;
import uet.oop.bomberman.GameBoard;

public abstract class Enemy extends Characters {
    public Enemy(int x, int y, GameBoard board) {
        super(x, y, board);
    }
}
