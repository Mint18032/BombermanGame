package uet.oop.bomberman.Game.entities.MapObjects;

import uet.oop.bomberman.Game.entities.Entity;
import uet.oop.bomberman.Level.Coordinates;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

public abstract class StandObject extends Entity {
    public StandObject(int x, int y, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    @Override
    public void update() {}

    @Override
    public void render(Screen screen) {
        screen.renderEntity(Coordinates.pixelToTile(x),Coordinates.tileToPixel(y),this);
    }

    @Override
    public boolean collision(Entity entity) {
    return false;
    }
}
