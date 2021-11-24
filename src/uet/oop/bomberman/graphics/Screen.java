package uet.oop.bomberman.graphics;

import javafx.scene.Scene;
import uet.oop.bomberman.Game.entities.Characters.Bomber;
import uet.oop.bomberman.Game.entities.Entity;
import uet.oop.bomberman.GameBoard;
import uet.oop.bomberman.GameLoop;

import java.awt.*;
import java.util.Arrays;

public class Screen {
    protected int width;
    protected int height;
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

    //Render Entity
    public void renderEntity(int xe, int ye, Entity entity) {
        xe -= offsetX;
        ye -= offsetY;
        for (int y = 0; y < entity.getSprite().getSize(); y++) {
            int ya = y + ye; //add offset
            for (int x = 0; x < entity.getSprite().getSize(); x++) {
                int xa = x + xe; //add offset
                if(xa < -entity.getSprite().getSize() || xa >= width || ya < 0 || ya >= height) break; //fix black margins
                if(xa < 0) xa = 0; //start at 0 from left
                int color = entity.getSprite().getPixel(x + y * entity.getSprite().getSize());
                if(color != transparentColor) pixels[xa + ya * width] = color;
            }
        }
    }

    //Render Entity with more than 1 Sprite in that place
    public void renderBelowEntity(int xe, int ye, Entity entity, Sprite belowSprite) {
        xe -= offsetX;
        ye -= offsetY;
        for (int y = 0; y < entity.getSprite().getSize(); y++) {
            int ya = y + ye; //add offset
            for (int x = 0; x < entity.getSprite().getSize(); x++) {
                int xa = x + xe; //add offset
                if(xa < -entity.getSprite().getSize() || xa >= width || ya < 0 || ya >= height) break; //fix black margins
                if(xa < 0) xa = 0; //start at 0 from left
                int color = entity.getSprite().getPixel(x + y * entity.getSprite().getSize());
                if(color != transparentColor)
                    pixels[xa + ya * width] = color;
                else
                    pixels[xa + ya * width] = belowSprite.getPixel(x + y * belowSprite.getSize());
            }
        }
    }

    public static void setOffset(int x0, int y0) {
        offsetX = x0;
        offsetY = y0;
    }

    public static int calculateXOffset(GameBoard board, Bomber bomber) {
        if(bomber == null) return 0;
        int temp = offsetX;

        double BomberX = bomber.getX() / 16;
        double complement = 0.5;
        int firstBreakpoint = board.getWidth() / 4;
        int lastBreakpoint = board.getWidth() - firstBreakpoint;

        if( BomberX > firstBreakpoint + complement && BomberX < lastBreakpoint - complement) {
            temp = (int)bomber.getX()  - (GameLoop.WIDTH / 2);
        }

        return temp;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getRealWidth() {
        return width * GameLoop.SCALE;
    }

    public int getRealHeight() {
        return height * GameLoop.SCALE;
    }

    public void drawCenteredString(String s, int w, int h, Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        int x = (w - fm.stringWidth(s)) / 2;
        int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
        g.drawString(s, x, y);
    }

    public void drawChangeLevel(Graphics g, int level) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getRealWidth(), getRealHeight());

        Font font = new Font("Arial", Font.PLAIN, 20 * GameLoop.SCALE);
        g.setFont(font);
        g.setColor(Color.white);
        drawCenteredString("LEVEL " + level, getRealWidth(), getRealHeight(), g);

    }

    public void drawEndGame(Graphics g, int points) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getRealWidth(), getRealHeight());

        Font font = new Font("Arial", Font.PLAIN, 20 * GameLoop.SCALE);
        g.setFont(font);
        g.setColor(Color.white);
        drawCenteredString("GAME OVER", getRealWidth(), getRealHeight(), g);

        font = new Font("Arial", Font.PLAIN, 10 * GameLoop.SCALE);
        g.setFont(font);
        g.setColor(Color.yellow);
        drawCenteredString("POINTS: " + points, getRealWidth(), getRealHeight() + (GameLoop.TILES_SIZE * 2) * GameLoop.SCALE, g);
    }

}
