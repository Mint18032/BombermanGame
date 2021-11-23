package uet.oop.bomberman.Game.entities.Characters.Enemy;

import uet.oop.bomberman.Game.entities.Bomb.Flame;
import uet.oop.bomberman.Game.entities.Characters.Bomber;
import uet.oop.bomberman.Game.entities.Characters.Characters;
import uet.oop.bomberman.Game.entities.Characters.Enemy.AutoMove.AM;
import uet.oop.bomberman.Game.entities.Characters.Enemy.AutoMove.AMEnemy;
import uet.oop.bomberman.Game.entities.Entity;
import uet.oop.bomberman.GameBoard;
import uet.oop.bomberman.GameLoop;
import uet.oop.bomberman.Level.Coordinates;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public abstract class Enemy extends Characters {

    protected int points;

    protected double speed;
    protected AM automove;

    protected final double MAX_STEPS;
    protected final double rest;
    protected double steps;

    protected int finalAnimation = 30;
    protected Sprite deadSprite;

    public Enemy(int x, int y, GameBoard gameBoard, Sprite dead, double speed, int points) {
        super(x, y, gameBoard);

        this.points = points;
        this.speed = speed;

        MAX_STEPS = GameLoop.TILES_SIZE / speed;
        rest = (MAX_STEPS - (int) MAX_STEPS) / MAX_STEPS;
        steps = MAX_STEPS;

        timeAfter = 20;
        deadSprite = dead;
    }

    @Override
    public void move(double xa, double ya) {
        if(!alive) return;
        y += ya;
        x += xa;
    }

    @Override
    public void update() {
        setAnimate();

        if(!alive) {
            afterKill();
            return;
        }

        if(alive)
            calcMove();
    }

    @Override
    public void calcMove() {
        // TODO: Tính toán hướng đi và di chuyển Enemy theo _ai và cập nhật giá trị cho _direction
        // TODO: sử dụng canMove() để kiểm tra xem có thể di chuyển tới điểm đã tính toán hay không
        // TODO: sử dụng move() để di chuyển
        // TODO: nhớ cập nhật lại giá trị cờ _moving khi thay đổi trạng thái di chuyển
        int xa = 0, ya = 0;
        if(steps <= 0){
            direction = automove.calcMove();
            steps = MAX_STEPS;
        }

        if(direction == 0) ya--;
        if(direction == 2) ya++;
        if(direction == 3) xa--;
        if(direction == 1) xa++;

        if(canMove(xa, ya)) {
            steps -= 1 + rest;
            move(xa * speed, ya * speed);
            moving = true;
        } else {
            steps = 0;
            moving = false;
        }
    }

    @Override
    public boolean canMove(double x, double y) {
        double xr = x, yr = y - 16; //subtract y to get more accurate results

        //the thing is, subract 15 to 16 (sprite size), so if we add 1 tile we get the next pixel tile with this
        //we avoid the shaking inside tiles with the help of steps
        if(direction == 0) { yr += sprite.getSize() -1 ; xr += sprite.getSize()/2; }
        if(direction == 1) {yr += sprite.getSize()/2; xr += 1;}
        if(direction == 2) { xr += sprite.getSize()/2; yr += 1;}
        if(direction == 3) { xr += sprite.getSize() -1; yr += sprite.getSize()/2;}

        int xx = Coordinates.pixelToTile(xr) +(int)x;
        int yy = Coordinates.pixelToTile(yr) +(int)y;

        Entity a = gameBoard.getEntity(xx, yy, this); //entity of the position we want to go

        return a.collision(this);
    }

    @Override
    public void render(Screen screen) {

        if(alive)
            chooseSprite();
        else {
            if(timeAfter > 0) {
                sprite = deadSprite;
                animate = 0;
            } else {
                sprite = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animate, 60);
            }

        }

        screen.renderEntity((int)x, (int)y - sprite.SIZE, this);
    }

    protected abstract void chooseSprite();

    @Override
    public boolean collision(Entity entity) {
        if(entity instanceof Flame){
            this.canKill();
            return false;
        }
        if(entity instanceof Bomber){
            ((Bomber) entity).canKill();
            return false;
        }
        return true;
    }

    @Override
    public void canKill() {
        if(!alive) return;
        alive = false;

        gameBoard.addPoints(points);

        Sound.play("AA126_11");
    }

    @Override
    public void afterKill() {
        if(timeAfter > 0) --timeAfter;
        else {
            if(finalAnimation > 0) --finalAnimation;
            else
                remove();
        }
    }
}
