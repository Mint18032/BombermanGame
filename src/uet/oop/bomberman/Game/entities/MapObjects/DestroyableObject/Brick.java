package uet.oop.bomberman.Game.entities.MapObjects.DestroyableObject;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game.entities.Entity;
import uet.oop.bomberman.Level.Coordinates;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends DestroyableObject {
    public Brick(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(Screen screen) {
        int x_ = Coordinates.tileToPixel(x);
        int y_ = Coordinates.pixelToTile(y);

        if(destroyed) {
            sprite = movingSprite(Sprite.brick_exploded,Sprite.brick_exploded1,Sprite.brick_exploded2);

        } else {
            screen.renderEntity(x_, y_, this);
        }
    }
}
