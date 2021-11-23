package uet.oop.bomberman.Game.entities.Characters.Enemy.AutoMove;

public class AMNormal extends AM{
    @Override
    public int calcMove() {
        return random.nextInt(4);
    }
}
