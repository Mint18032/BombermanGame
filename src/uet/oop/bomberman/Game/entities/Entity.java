package uet.oop.bomberman.Game.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Level.Coordinates;
import uet.oop.bomberman.graphics.Render;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity implements Render {
    protected double x;

    protected double y;

    protected boolean removed = false;
    protected Sprite sprite;

    @Override
    public abstract void update();

    @Override
    public abstract void render(Screen screen);

    public void remove() {
        removed = true;
    }

    public boolean Removed() {
        return removed;
    }

    public Sprite getSprite() {
        return sprite;
    }
    //Check collision.
    public abstract boolean collision(Entity entity);

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getXTile() {
        return Coordinates.pixelToTile(x + sprite.SIZE / 2);
    }

    public int getYTile() {
        return Coordinates.pixelToTile(y - sprite.SIZE / 2);
    }
}
