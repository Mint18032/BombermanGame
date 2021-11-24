package uet.oop.bomberman;

import com.oracle.deploy.update.UpdateCheck;
import javafx.scene.Scene;
import uet.oop.bomberman.Frame.NortificationPanel;
import uet.oop.bomberman.Game.entities.Bomb.Bomb;
import uet.oop.bomberman.Game.entities.Bomb.FlameSegment;
import uet.oop.bomberman.Game.entities.Characters.Bomber;
import uet.oop.bomberman.Game.entities.Characters.Characters;
import uet.oop.bomberman.Game.entities.Entity;
import uet.oop.bomberman.Game.entities.Nortification;
import uet.oop.bomberman.Input.InputKeyboard;
import uet.oop.bomberman.Level.LoadFileLevel;
import uet.oop.bomberman.Level.LoadLevel;
import uet.oop.bomberman.graphics.Render;
import uet.oop.bomberman.graphics.Screen;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
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

    //Update
    @Override
    public void update() {
        if( game.isPaused() ) return;

        updateEntities();
        updateCharacters();
        updateBombs();
        detectEndGame();

        for (int i = 0; i < character.size(); i++) {
            Characters a = character.get(i);
            if(a.Removed()) character.remove(i);
        }
    }

    protected void updateEntities() {
        if( game.isPaused() ) return;
        for (int i = 0; i < entity.length; i++) {
            entity[i].update();
        }
    }

    protected void updateCharacters() {
        if( game.isPaused() ) return;
        Iterator<Characters> itr = character.iterator();

        while(itr.hasNext() && !game.isPaused())
            itr.next().update();
    }

    protected void updateBombs() {
        if( game.isPaused() ) return;
        Iterator<Bomb> itr = bomb.iterator();

        while(itr.hasNext())
            itr.next().update();
    }

    //Render
    @Override
    public void render(Screen screen) {
        if( game.isPaused() ) return;

        //only render the visible part of screen
        int x0 = Screen.offsetX >> 4;
        int x1 = (Screen.offsetX + screen.getWidth() + GameLoop.TILES_SIZE) / GameLoop.TILES_SIZE;
        int y0 = Screen.offsetY >> 4;
        int y1 = (Screen.offsetY + screen.getHeight()) / GameLoop.TILES_SIZE;

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                entity[x + y * loadLevel.getWidth()].render(screen);
            }
        }

        renderBombs(screen);
        renderCharacter(screen);
    }

    public void renderBombs(Screen screen) {
        Iterator<Bomb> itr = bomb.iterator();

        while (itr.hasNext()) {
            itr.next().render(screen);
        }
    }

    public void renderCharacter(Screen screen) {
        Iterator<Characters> itr = character.iterator();

        while (itr.hasNext()) {
            itr.next().render(screen);
        }
    }

    public int subtractTime() {
        if(game.isPaused())
            return this.time;
        else
            return this.time--;
    }

    //Level
    public void loadLevel(int level) {
        time = GameLoop.TIME;
        showScreen = 2;
        game.resetScreenDelay();
        game.pause();
        character.clear();
        bomb.clear();
        loadLevel = new LoadFileLevel(this, level);
        entity = new Entity[loadLevel.getHeight() * loadLevel.getWidth()];
        loadLevel.createEntities();
    }

    public void nextLevel() {
        GameLoop.setBombRadius(1);
        GameLoop.setBombRate(1);
        GameLoop.setBomberSpeed(1.0);
        loadLevel(loadLevel.getLevel() + 1);
    }

    //EndGame
    protected void detectEndGame() {
        if(time <= 0)
            endGame();
    }

    public void endGame() {
        showScreen = 1;
        game.resetScreenDelay();
        game.pause();
    }

    //Screen
    public void drawScreen(Graphics g) {
        switch (showScreen) {
            case 1:
                screen.drawEndGame(g, points);
                break;
            case 2:
                screen.drawChangeLevel(g, loadLevel.getLevel());
                break;
        }
    }
    //Add
    public Entity getEntity(double x, double y, Characters m) {

        Entity res = null;

        res = getFlameSegmentAt((int)x, (int)y);
        if( res != null) return res;

        res = getBombAt(x, y);
        if( res != null) return res;

        res = getCharacterAtExcluding((int)x, (int)y, m);
        if( res != null) return res;

        res = getEntityAt((int)x, (int)y);

        return res;
    }

    public List<Bomb> getBombs() {
        return bomb;
    }

    public Bomb getBombAt(double x, double y) {
        Iterator<Bomb> bs = bomb.iterator();
        Bomb b;
        while(bs.hasNext()) {
            b = bs.next();
            if(b.getX() == (int)x && b.getY() == (int)y)
                return b;
        }

        return null;
    }

    public Bomber getBomber() {
        Iterator<Characters> itr = character.iterator();

        Characters cur;
        while(itr.hasNext()) {
            cur = itr.next();

            if(cur instanceof Bomber)
                return (Bomber) cur;
        }

        return null;
    }

    public Characters getCharacterAtExcluding(int x, int y, Characters a) {
        Iterator<Characters> itr = character.iterator();

        Characters cur;
        while(itr.hasNext()) {
            cur = itr.next();
            if(cur == a) {
                continue;
            }

            if(cur.getXTile() == x && cur.getYTile() == y) {
                return cur;
            }

        }

        return null;
    }

    public FlameSegment getFlameSegmentAt(int x, int y) {
        Iterator<Bomb> bs = bomb.iterator();
        Bomb b;
        while(bs.hasNext()) {
            b = bs.next();

            FlameSegment e = b.flameAt(x, y);
            if(e != null) {
                return e;
            }
        }

        return null;
    }

    public Entity getEntityAt(double x, double y) {
        return entity[(int)x + (int)y * loadLevel.getWidth()];
    }

    public void addEntity(int pos, Entity e) {
        entity[pos] = e;
    }

    public void addCharacter(Characters e) {
        character.add(e);
    }

    public InputKeyboard getInput() {
        return input;
    }

    public LoadLevel getLevel() {
        return loadLevel;
    }

    public GameLoop getGame() {
        return game;
    }

    public int getShow() {
        return showScreen;
    }

    public void setShow(int i) {
        showScreen = i;
    }

    public void addBomb(Bomb e) {
        bomb.add(e);
    }

    public List<Bomb> getBomb() {return bomb;}

    public int getWidth() {
        return loadLevel.getWidth();
    }

    public int getHeight() {
        return loadLevel.getHeight();
    }

    public int getTime() {
        return this.time;
    }

    public int getPoints() {
        return points;
    }
}
