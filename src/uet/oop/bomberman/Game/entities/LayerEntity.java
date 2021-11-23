package uet.oop.bomberman.Game.entities;

import uet.oop.bomberman.Game.entities.MapObjects.DestroyableObject.DestroyableObject;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

import java.util.LinkedList;

public class LayerEntity extends Entity{

    protected LinkedList<Entity> _entities = new LinkedList<>();

    public LayerEntity(int x, int y, Entity ... entity) {
        this.x = x;
        this.y = y;

        for (int i = 0; i < entity.length; i++) {
            _entities.add(entity[i]);

            if(i > 1) {
                if(entity[i] instanceof DestroyableObject)
                    ((DestroyableObject)entity[i]).addBelowSprite(entity[i-1].getSprite());
            }
        }
    }

    @Override
    public void update() {
        clearRemoved();
        getTopEntity().update();
    }

    public Entity getTopEntity() {

        return _entities.getLast();
    }

    private void clearRemoved() {
        Entity top  = getTopEntity();

        if(top.Removed())  {
            _entities.removeLast();
        }
    }

    @Override
    public void render(Screen screen) {
        getTopEntity().render(screen);
    }

    @Override
    public boolean collision(Entity entity) {
        return getTopEntity().collision(entity);
    }
}
