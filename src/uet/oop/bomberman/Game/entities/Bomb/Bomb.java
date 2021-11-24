package uet.oop.bomberman.Game.entities.Bomb;

import uet.oop.bomberman.Game.entities.Animation;
import uet.oop.bomberman.Game.entities.Characters.Bomber;
import uet.oop.bomberman.Game.entities.Characters.Characters;
import uet.oop.bomberman.Game.entities.Entity;
import uet.oop.bomberman.GameBoard;
import uet.oop.bomberman.GameLoop;
import uet.oop.bomberman.Level.Coordinates;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
public class Bomb extends Animation {

	protected double _timeToExplode = 120; //2 seconds - thoi gian phat no
	public int _timeAfter = 20;// thoi gian de no
	
	protected GameBoard _Game_board;
	protected Flame[] _flames;
	protected boolean _exploded = false;
	protected boolean _allowedToPassThru = true;
	 
	public Bomb(int x, int y, GameBoard gameBoard) {
		_x = x;
		_y = y;
		_Game_board = gameBoard;
		_sprite = Sprite.bomb;
	}
	
	@Override
	public void update() {
		if(_timeToExplode > 0) 
			_timeToExplode--;
		else {
			if(!_exploded) 
				explode();
			else
				updateFlames();
			
			if(_timeAfter > 0) 
				_timeAfter--;
			else
				remove();
		}
			
		animate();
	}
	
	@Override
	public void render(Screen screen) {
		if(_exploded) {
			_sprite =  Sprite.bomb_exploded2;
			renderFlames(screen);
		} else
			_sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, _animate, 60);
		
		int xt = (int)_x << 4;
		int yt = (int)_y << 4;
		
		screen.renderEntity(xt, yt , this);
	}
	
	public void renderFlames(Screen screen) {
		for (int i = 0; i < _flames.length; i++) {
			_flames[i].render(screen);
		}
	}
	
	public void updateFlames() {
		for (int i = 0; i < _flames.length; i++) {
			_flames[i].update();
		}
	}

    /**
     * Xử lý Bomb nổ
     */
	protected void explode() {
		_exploded = true;
		_allowedToPassThru = true;
		Characters x = _Game_board.getCharacterAtExcluding((int)_x, (int)_y, null);
                if(x != null){
                    x.kill();
                }
                _flames = new Flame[4];
                for (int i = 0; i < _flames.length; i++) {
                    _flames[i] = new Flame((int) _x, (int) _y, i, GameLoop.getBombRadius(), _Game_board);
                }
	}
        public void time_explode() {
		_timeToExplode = 0;
	}
	public FlameSegment flameAt(int x, int y) {
		if(!_exploded) return null;
		
		for (int i = 0; i < _flames.length; i++) {
			if(_flames[i] == null) return null;
			FlameSegment e = _flames[i].flameSegmentAt(x, y);
			if(e != null) return e;
		}
		
		return null;
	}

	@Override
	public boolean collide(Entity e) {
        
        if(e instanceof Bomber) {
			double diffX = e.getX() - Coordinates.tileToPixel(getX());
			double diffY = e.getY() - Coordinates.tileToPixel(getY());
			
			if(!(diffX >= -10 && diffX < 16 && diffY >= 1 && diffY <= 28)) { // differences to see if the player has moved out of the bomb, tested values
				_allowedToPassThru = false;
			}
			
			return _allowedToPassThru;
		}
		if(e instanceof Flame ) {
			time_explode();
			return true;
		}
		return false;
	}
}
