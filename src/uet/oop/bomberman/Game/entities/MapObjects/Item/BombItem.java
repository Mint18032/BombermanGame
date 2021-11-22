package uet.oop.bomberman.Game.entities.MapObjects.Item;

import uet.oop.bomberman.Game.entities.Characters.Bomber;
import uet.oop.bomberman.Game.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class BombItem extends Item{
    public BombItem(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public boolean collision(Entity entity) {
        if( entity instanceof Bomber ) {
            remove();
        }
        return false;
    }
}
