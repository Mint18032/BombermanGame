/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uet.oop.bomberman.Game.entities.Characters.Enemy;

import uet.oop.bomberman.GameBoard;
import uet.oop.bomberman.Game.entities.Characters.Enemy.AutoMove.AMEnemy;
import uet.oop.bomberman.graphics.Sprite;


public class Doll extends Enemy{

    public Doll(int x, int y, GameBoard gameBoard) {
        super(x, y, gameBoard, Sprite.oneal_dead, 0.8, 100);

        _sprite = Sprite.balloom_left1;

        _AM = new AMEnemy(_Game_board.getBomber(), this);
        _direction = _AM.calculateDirection();
    }

    @Override
    protected void chooseSprite() {
        switch (_direction) {
            case 0:
            case 1:
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, _animate, 60);
                } else {
                    _sprite = Sprite.doll_left1;
                }
                break;
            case 2:
            case 3:
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, _animate, 60);
                } else {
                    _sprite = Sprite.doll_left1;
                }
                break;
        }
    }
}
