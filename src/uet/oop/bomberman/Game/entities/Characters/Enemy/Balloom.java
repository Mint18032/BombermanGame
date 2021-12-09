package uet.oop.bomberman.Game.entities.Characters.Enemy;

import uet.oop.bomberman.GameBoard;
import uet.oop.bomberman.Game.entities.Characters.Enemy.AutoMove.AMNormal;
import uet.oop.bomberman.graphics.Sprite;

public class Balloom extends Enemy {
	
	public Balloom(int x, int y, GameBoard gameBoard) {
		super(x, y, gameBoard, Sprite.balloom_dead, 0.5, 100);
		
		_sprite = Sprite.balloom_left1;
		
		_AM = new AMNormal();
		_direction = _AM.calculateDirection();
                
	}

	/**
	 * Chọn hình để hiện theo hướng di chuyển.
	 */
	@Override
	protected void chooseSprite() {
		switch(_direction) {
			case 0:
			case 1:
					_sprite = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, _animate, 60);
				break;
			case 2:
			case 3:
					_sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, _animate, 60);
				break;
		}
	}
}
