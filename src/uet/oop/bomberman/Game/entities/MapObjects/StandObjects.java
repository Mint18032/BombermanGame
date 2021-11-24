package uet.oop.bomberman.Game.entities.MapObjects;

import uet.oop.bomberman.Game.entities.Entity;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Level.Coordinates;

/**
 * Entity cố định, không di chuyển
 */
public abstract class StandObjects extends Entity {
	
	public StandObjects(int x, int y, Sprite sprite) {
		_x = x;
		_y = y;
		_sprite = sprite;
	}

	/**
	 * Mặc định không cho bất cứ một đối tượng nào đi qua
	 * @param e
	 * @return
	 */
	@Override
	public boolean collide(Entity e) {
		return false;//khong cho di qua
	}
	
	@Override
	public void render(Screen screen) {
		screen.renderEntity( Coordinates.tileToPixel(_x), Coordinates.tileToPixel(_y), this);
	}
	
	@Override
	public void update() {}
}
