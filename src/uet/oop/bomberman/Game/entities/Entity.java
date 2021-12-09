package uet.oop.bomberman.Game.entities;

import uet.oop.bomberman.graphics.Render;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Level.Coordinates;

/**
 * Thực thể, Cơ bản của tất cả các đối tượng trên Game.
 */
public abstract class Entity implements Render {

	protected double _x, _y;
	protected boolean _removed = false;
	protected Sprite _sprite;

	@Override
	public abstract void update();

	@Override
	public abstract void render(Screen screen);
	
	public void remove() {
		_removed = true;
	}
	
	public boolean isRemoved() {
		return _removed;
	}
	
	public Sprite getSprite() {
		return _sprite;
	}

	public abstract boolean collide(Entity e);

	public double getX() {
		return _x;
	}
	
	public double getY() {
		return _y;
	}
	
	public int getXTile() {
		return Coordinates.pixelToTile(_x + _sprite.SIZE / 2);
	}
	
	public int getYTile() {
		return Coordinates.pixelToTile(_y - _sprite.SIZE / 2);
	}

    
}
