package uet.oop.bomberman.Game.entities.MapObjects;


import uet.oop.bomberman.Game.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

/**
 * Cỏ.
 */
public class Grass extends StandObjects {

	public Grass(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

	@Override
	public boolean collide(Entity e) {
		return true;
	}
}
