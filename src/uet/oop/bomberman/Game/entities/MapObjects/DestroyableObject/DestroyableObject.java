package uet.oop.bomberman.Game.entities.MapObjects.DestroyableObject;

import javafx.scene.Scene;
import uet.oop.bomberman.Game.entities.Bomb.Flame;
import uet.oop.bomberman.Game.entities.Entity;
import uet.oop.bomberman.Game.entities.MapObjects.StandObject;
import uet.oop.bomberman.Level.Coordinates;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

public class DestroyableObject extends StandObject {
    private final int MaxFps = 10000;
    private int animattion = 0;
    public boolean destroyed = false;
    public int removTime = 20;
    public Sprite belowSprite = Sprite.grass;

    public DestroyableObject(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public void setDestroyed() {
        destroyed = true;
    }

    @Override
    public boolean collision(Entity entity) {
        if(entity instanceof Flame) setDestroyed();
        return false;
    }

    @Override
    public void update() {
        if(destroyed) {
            if(animattion < MaxFps) {
                animattion++;
            }
            else {
                animattion = 0;
            }
            if(removTime > 0) removTime --;
            else remove();
        }
    }

    protected Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2) {
        int calc = animattion%30;
        if(calc < 10) {
            return normal;
        }
        if (calc < 20) {
            return x1;
        }
        return  x2;
    }

    public void addBelowSprite(Sprite sprite) {
        belowSprite = sprite;
    }
}
