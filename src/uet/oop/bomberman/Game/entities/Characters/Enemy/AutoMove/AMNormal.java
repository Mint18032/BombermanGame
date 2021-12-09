package uet.oop.bomberman.Game.entities.Characters.Enemy.AutoMove;

/**
 * Tự động di chuyển bình thường (chỉ random).
 */
public class AMNormal extends AM {

	@Override
	public int calculateDirection() {
		return random.nextInt(4);
	}

}
