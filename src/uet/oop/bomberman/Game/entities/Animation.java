package uet.oop.bomberman.Game.entities;

import javafx.scene.image.Image;

public abstract class Animation extends Entity {
    protected int animate = 0;
    protected final int Max = 10000;

    protected void setAnimate() {
        if(animate < Max) {
            animate++;
        } else animate = 0;
    }
}
