package uet.oop.bomberman.graphics;

import javafx.scene.Scene;
import uet.oop.bomberman.Game.entities.Entity;

import java.util.Arrays;

public class Screen {
    protected int width, height;
    private final int transparentColor = 0xffff_00ff;
    public int[] pixels;

    public static int offsetX = 0, offsetY = 0;

    public Screen (int width, int height) {
        this.height = height;
        this.width = width;
        pixels = new int[width * height];
    }

    public void clear() {
        Arrays.fill(pixels, 0);
    }

    public void renderEntity(int xe, int ye, Entity entity) {
        xe -= offsetX;
        ye -= offsetY;
    }
}
