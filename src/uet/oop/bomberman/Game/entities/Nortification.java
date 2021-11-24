package uet.oop.bomberman.Game.entities;

import uet.oop.bomberman.graphics.Screen;

import java.awt.*;

public class Nortification extends Entity {
    protected String message;
    protected int duration;
    protected Color color;
    protected int size;

    public Nortification(String message, double x, double y, int duration, Color color, int size) {
        this.x =x;
        this.y = y;
        this.message = message;
        this.duration = duration * 60; //seconds
        this.color = color;
        this.size = size;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int _duration) {
        this.duration = _duration;
    }

    public String getMessage() {
        return message;
    }

    public Color getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Screen screen) {
    }

    @Override
    public boolean collision(Entity e) {
        return true;
    }


}
