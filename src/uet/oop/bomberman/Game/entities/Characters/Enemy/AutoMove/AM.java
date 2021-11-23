package uet.oop.bomberman.Game.entities.Characters.Enemy.AutoMove;

import java.util.Random;

//Auto Move for Enemy
public abstract class AM {
    protected Random random = new Random();

    public abstract int calcMove();
}
