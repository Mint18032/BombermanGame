package uet.oop.bomberman.Game.entities;

import javafx.scene.image.Image;

public abstract class Animation extends Entity {
    protected int animate = 0;
    protected final int Max = 7500;

    public Animation(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    protected void setAnimate() {
        if(animate < Max) {
            animate++;
        } else animate = 0;
    }
}
