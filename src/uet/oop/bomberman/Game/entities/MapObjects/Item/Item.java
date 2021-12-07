package uet.oop.bomberman.Game.entities.MapObjects.Item;

import uet.oop.bomberman.Game.entities.MapObjects.StandObjects;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Item extends StandObjects {
	protected int _duration = -1; //thoi gian cua item ,-1 la vo han
	protected boolean _active = false;
	protected int _level;
	public Item(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

}