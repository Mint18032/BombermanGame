package uet.oop.bomberman.Game.entities.Characters.Enemy.AutoMove;

import java.util.Random;

/**
 * Xử lý di chuyển tự động của các nhân vật.
 */
public abstract class AM {
	
	protected Random random = new Random();

	/**
	 * Thuật toán tìm đường đi.
	 * @return một trong các giá trị 0/1/2/3 tương ứng với hướng đi lên/phải/xuống/trái (chiều kim đồng hồ).
	 */
	public abstract int calculateDirection();
}
