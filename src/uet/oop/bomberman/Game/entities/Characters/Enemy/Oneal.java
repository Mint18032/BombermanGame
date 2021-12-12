package uet.oop.bomberman.Game.entities.Characters.Enemy;


import uet.oop.bomberman.Game.entities.Characters.Enemy.AutoMove.AMNormal;
import uet.oop.bomberman.GameBoard;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {
	public Oneal(int x, int y, GameBoard gameBoard) {
		super(x, y, gameBoard, Sprite.doll_dead, 0.8 , 100);
		
		_sprite = Sprite.balloom_left1;
		
		_AM = new AMNormal();
		_direction = _AM.calculateDirection();
                
	}
	
	@Override
	protected void chooseSprite() {
		switch(_direction) {
			case 0:
			case 1:
				if(_moving)
					_sprite = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, _animate, 60);
				else
					_sprite = Sprite.oneal_left1;
				break;
			case 2:
			case 3:
				if(_moving)
					_sprite = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, _animate, 60);
				else
					_sprite = Sprite.oneal_left1;
				break;
		}
	}
}
