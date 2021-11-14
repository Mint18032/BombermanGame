package uet.oop.bomberman.Game.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    private Sprite moveUp;
    private Sprite moveDown;
    private Sprite moveLeft;
    private Sprite moveRight;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {
    }

    public void move() {
        switch (Input.INSTANCE.getCode()) {
            case UP:
            case DOWN:
            case LEFT:
            case RIGHT:
        }
    }
}
