package uet.oop.bomberman.Game.entities.MapObjects.Item;

import uet.oop.bomberman.Game.entities.Characters.Bomber;
import uet.oop.bomberman.Game.entities.Entity;
import uet.oop.bomberman.GameLoop;
import uet.oop.bomberman.graphics.Sprite;

/**
 * Item tăng tốc Bomber.
 */
public class SpeedItem extends Item {

	public SpeedItem(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

	@Override
	public boolean collide(Entity e) {
            if (e instanceof Bomber) {
                GameLoop.addBomberSpeed(0.5);
                remove();
            }
        return false;
	}
}
