package uet.oop.bomberman.Game.entities.MapObjects;

import uet.oop.bomberman.GameBoard;
import uet.oop.bomberman.Game.entities.Entity;
import uet.oop.bomberman.Game.entities.Characters.Bomber;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Sound.Sound;

public class Portal extends StandObjects {
	protected GameBoard _Game_board;

	public Portal(int x, int y, GameBoard gameBoard, Sprite sprite) {
		super(x, y, sprite);
                _Game_board = gameBoard;
	}
	
	@Override
	public boolean collide(Entity e) {
		if(e instanceof Bomber ) {
			
			if(_Game_board.detectNoEnemies() == false)
				return false;
			
			if(e.getXTile() == getX() && e.getYTile() == getY()) {
				if(_Game_board.detectNoEnemies()){
					_Game_board.nextLevel();
					Sound levelUp = new Sound("levelUp");
					levelUp.play();
				}
			}
			
			return true;
		}
		
		return true;
	}

}
