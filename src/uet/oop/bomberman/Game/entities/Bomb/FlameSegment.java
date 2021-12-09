package uet.oop.bomberman.Game.entities.Bomb;

import uet.oop.bomberman.Game.entities.Entity;
import uet.oop.bomberman.Game.entities.Characters.Bomber;
import uet.oop.bomberman.Game.entities.Characters.Enemy.Enemy;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

/**
 * Các Segment của Flame.
 */
public class FlameSegment extends Entity {

	protected boolean _last;

	public FlameSegment(int x, int y, int direction, boolean last) {
		_x = x;
		_y = y;
		_last = last;

		switch (direction) {
			case 0:
				if(!last) {
					_sprite = Sprite.explosion_vertical2;
				} else {
					_sprite = Sprite.explosion_vertical_top_last2;
				}
			break;
			case 1:
				if(!last) {
					_sprite = Sprite.explosion_horizontal2;
				} else {
					_sprite = Sprite.explosion_horizontal_right_last2;
				}
				break;
			case 2:
				if(!last) {
					_sprite = Sprite.explosion_vertical2;
				} else {
					_sprite = Sprite.explosion_vertical_down_last2;
				}
				break;
			case 3: 
				if(!last) {
					_sprite = Sprite.explosion_horizontal2;
				} else {
					_sprite = Sprite.explosion_horizontal_left_last2;
				}
				break;
		}
	}
	
	@Override
	public void render(Screen screen) {
		int xt = (int)_x << 4;
		int yt = (int)_y << 4;
		
		screen.renderEntity(xt, yt , this);
	}
	
	@Override
	public void update() {}

	@Override
	public boolean collide(Entity e) {
		if(e instanceof Bomber) ((Bomber) e).kill();
		if(e instanceof Enemy) ((Enemy) e).kill();
		return true;
	}
}