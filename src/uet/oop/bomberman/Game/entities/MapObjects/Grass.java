package uet.oop.bomberman.Game.entities.MapObjects;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Grass extends StandObject {

    public Grass(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public boolean collision(Entity entity) {
        return true;
    }
}
