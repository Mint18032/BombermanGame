package uet.oop.bomberman;

import javafx.scene.Scene;
import uet.oop.bomberman.Game.entities.Bomb.Bomb;
import uet.oop.bomberman.Game.entities.Characters.Characters;
import uet.oop.bomberman.Game.entities.Entity;
import uet.oop.bomberman.Input.InputKeyboard;
import uet.oop.bomberman.Level.LoadLevel;
import uet.oop.bomberman.graphics.Render;
import uet.oop.bomberman.graphics.Screen;

import java.util.ArrayList;
import java.util.List;

public class GameBoard implements Render {

    protected GameLoop game;
    protected InputKeyboard input;
    protected Screen screen;
    protected LoadLevel loadLevel;

    public Entity[] entity;
    public List<Characters> character = new ArrayList<>();
    protected List<Bomb> bomb = new ArrayList<>();

    private int showScreen = -1;

    private int time = GameLoop.TIME;
    private int points = GameLoop.POINTS;

    public GameBoard(GameLoop game, InputKeyboard input, Screen screen) {
        this.game = game;
        this.input = input;
        this.screen = screen;

        loadLevel(1);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Screen screen) {

    }
}
