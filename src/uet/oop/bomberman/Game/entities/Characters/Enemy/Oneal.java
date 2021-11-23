package uet.oop.bomberman.Game.entities.Characters.Enemy;

import uet.oop.bomberman.Game.entities.Characters.Enemy.AutoMove.AMNormal;
import uet.oop.bomberman.GameBoard;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy{


    public Oneal(int x, int y, GameBoard gameBoard) {
        super(x, y, gameBoard, Sprite.balloom_dead, 0.8 , 100);

        sprite = Sprite.balloom_left1;
        automove = new AMNormal();
        direction = automove.calcMove();

    }

    @Override
    protected void chooseSprite() {
        switch(direction) {
            case 0:
            case 1:
                if(moving)
                    sprite = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, 60);
                else
                    sprite = Sprite.oneal_left1;
                break;
            case 2:
            case 3:
                if(moving)
                    sprite = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, 60);
                else
                    sprite = Sprite.oneal_left1;
                break;
        }
    }
}
