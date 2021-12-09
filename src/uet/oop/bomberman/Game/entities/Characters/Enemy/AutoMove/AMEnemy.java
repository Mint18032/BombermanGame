package uet.oop.bomberman.Game.entities.Characters.Enemy.AutoMove;

import uet.oop.bomberman.Game.entities.Characters.Bomber;
import uet.oop.bomberman.Game.entities.Characters.Enemy.Enemy;

/**
 * Di chuyển tự động của Enemy.
 */
public class AMEnemy extends AM {
	Bomber _bomber;
	Enemy _e;
	
	public AMEnemy(Bomber bomber, Enemy e) {
		_bomber = bomber;
		_e = e;
	}

	/**
	 * Tính toán hướng đi tự động.
	 */
	@Override
	public int calculateDirection() {
		if(_bomber == null)
			return random.nextInt(4);
		
		int vertical = random.nextInt(2);
		
		if(vertical == 1) {
			int v = calculateRowDirection();
			if(v != -1)
				return v;
			else
				return calculateColDirection();
		} else {
			int h = calculateColDirection();
			if(h != -1)
				return h;
			else
				return calculateRowDirection();
		}
	}

	/**
	 * @return 3 - trái hoặc 1 - phải.
	 */
	protected int calculateColDirection() {
		if(_bomber.getXTile() < _e.getXTile())
			return 3;
		else if(_bomber.getXTile() > _e.getXTile())
			return 1;
		
		return -1;
	}

	/**
	 * @return 0 - lên hoặc 2 - xuống.
	 */
	protected int calculateRowDirection() {
		if(_bomber.getYTile() < _e.getYTile())
			return 0;
		else if(_bomber.getYTile() > _e.getYTile())
			return 2;
		return -1;
	}

}
