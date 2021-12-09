package uet.oop.bomberman.Game.entities.MapObjects.Item;

import uet.oop.bomberman.Game.entities.Characters.Bomber;
import uet.oop.bomberman.Game.entities.Entity;
import uet.oop.bomberman.GameLoop;
import uet.oop.bomberman.graphics.Sprite;

/**
 * Item tăng số lượng Bom của Bomber.
 */
public class BombItem extends Item {

	public BombItem(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

	@Override
	public boolean collide(Entity e) {
            if (e instanceof Bomber) {

                GameLoop.addBombRate(1);
                remove();
            }
        return false;
	}

}
