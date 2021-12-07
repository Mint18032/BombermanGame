package uet.oop.bomberman.Game.entities;

import uet.oop.bomberman.Sound.Sound;

/**
 * Entity có hiệu ứng hoạt hình
 */
public abstract class Animation extends Entity {

	protected int _animate = 0;
	protected final int MAX_ANIMATE = 7500;
	protected Sound sound;

	protected void animate() {
		if(_animate < MAX_ANIMATE) _animate++;
		else _animate = 0;
	}
}
