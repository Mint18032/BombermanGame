package uet.oop.bomberman.Game.entities.Characters;

import javafx.scene.image.Image;
import jdk.internal.util.xml.impl.Input;
import uet.oop.bomberman.Game.entities.Bomb.Bomb;
import uet.oop.bomberman.Game.entities.Entity;
import uet.oop.bomberman.Game.entities.MapObjects.Item.Item;
import uet.oop.bomberman.GameBoard;
import uet.oop.bomberman.Input.InputKeyboard;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends Characters {

    private List<Bomb> bombs;
    protected InputKeyboard input;
    public static List<Item> items = new ArrayList<Item>();\


    public Bomber (int x, int y, GameBoard gameBoard) {
        super(x, y, gameBoard);
        sprite = Sprite.player_right;
    }

    @Override
    public void calcMove() {

    }

    @Override
    public void canMove(double _x, double _y) {

    }

    @Override
    public void move(double xmove, double ymove) {

    }

    @Override
    public void canKill() {

    }

    @Override
    public void afterKill() {

    }

    @Override
    public void update() {
    }

    @Override
    public void render(Screen screen) {

    }

    @Override
    public boolean collision(Entity entity) {
        return false;
    }
}
