package uet.oop.bomberman.Game.entities.Characters.Enemy.AutoMove;

public class AMNormal extends AM {

	@Override
	public int calculateDirection() {
		return random.nextInt(4);
	}

}
