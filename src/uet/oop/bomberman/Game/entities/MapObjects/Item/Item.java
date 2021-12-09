package uet.oop.bomberman.Game.entities.MapObjects.Item;

import uet.oop.bomberman.Game.entities.MapObjects.StandObjects;
import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Item extends StandObjects {

	public Item(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

	@Override
	public void remove() {
		_removed = true;

		Sound sound = new Sound("claiming");
		sound.play();
	}
}