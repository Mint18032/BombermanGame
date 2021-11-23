package uet.oop.bomberman.Game.entities.Characters.Enemy;

import uet.oop.bomberman.Game.entities.Characters.Enemy.AutoMove.AMNormal;
import uet.oop.bomberman.GameBoard;
import uet.oop.bomberman.graphics.Sprite;

public class Balloom extends Enemy{

    public Balloom (int x, int y, GameBoard gameBoard) {
        super(x, y, gameBoard, Sprite.balloom_dead,0.5, 100);
        sprite = Sprite.balloom_left1;
        automove = new AMNormal();
        direction = automove.calcMove();
    }
    @Override
    protected void chooseSprite() {
        switch(direction) {
            case 0:
            case 1:
                sprite = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate, 60);
                break;
            case 2:
            case 3:
                sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 60);
                break;
        }
    }
}
