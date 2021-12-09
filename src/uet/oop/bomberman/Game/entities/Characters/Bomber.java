package uet.oop.bomberman.Game.entities.Characters;

import java.util.ArrayList;
import uet.oop.bomberman.GameBoard;
import uet.oop.bomberman.GameLoop;
import uet.oop.bomberman.Game.entities.Entity;
import uet.oop.bomberman.Game.entities.Bomb.Bomb;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Input.InputKeyboard;

import java.util.Iterator;
import java.util.List;
import uet.oop.bomberman.Game.entities.LayerEntity;
import uet.oop.bomberman.Game.entities.Bomb.Flame;
import uet.oop.bomberman.Game.entities.Characters.Enemy.Enemy;
import uet.oop.bomberman.Game.entities.MapObjects.Item.Item;
import uet.oop.bomberman.Level.Coordinates;
import uet.oop.bomberman.Sound.Sound;

public class Bomber extends Characters {

    private List<Bomb> _bombs;
    protected InputKeyboard _input;

    protected int _timeBetweenPutBombs = 0;

    public Bomber(int x, int y, GameBoard gameBoard) {
        super(x, y, gameBoard);
        _bombs = _Game_board.getBombs();
        _input = _Game_board.getInput();
        _sprite = Sprite.player_right;
        sound = new Sound("moving");
    }

    @Override
    public void update() {
        clearBombs();
        if (!_alive) {
            afterKill();
            return;
        }

        if (_timeBetweenPutBombs < -7500) _timeBetweenPutBombs = 0;
        else _timeBetweenPutBombs--;

        animate();
        calculateMove();
        detectPlaceBomb();
    }

    @Override
    public void render(Screen screen) {
        calculateXOffset();

        if (_alive)
            chooseSprite();
        else
            _sprite = Sprite.player_dead1;

        screen.renderEntity((int) _x, (int) _y - _sprite.SIZE, this);
        sound.play();
    }

    public void calculateXOffset() {
        int xScroll = Screen.calculateXOffset(_Game_board, this);
        Screen.setOffset(xScroll, 0);
    }

    /**
     * Kiểm tra xem có đặt được bom hay không? nếu có thì đặt bom tại vị trí hiện tại của Bomber
     */
    private void detectPlaceBomb() {
        if(_input.space && GameLoop.getBombRate() > 0 && _timeBetweenPutBombs < 0) {
			
			int xt = Coordinates.pixelToTile(_x + _sprite.getSize() / 2);
			int yt = Coordinates.pixelToTile( (_y + _sprite.getSize() / 2) - _sprite.getSize() ); //subtract half player height and minus 1 y position
			
			placeBomb(xt,yt);
			GameLoop.addBombRate(-1);
			
			_timeBetweenPutBombs = 30;
		}
    }

    protected void placeBomb(int x, int y) {
        Bomb b = new Bomb(x, y, _Game_board);
        _Game_board.addBomb(b);
    }

    private void clearBombs() {
        Iterator<Bomb> bs = _bombs.iterator();

        Bomb b;
        while (bs.hasNext()) {
            b = bs.next();
            if (b.isRemoved()) {
                bs.remove();
                GameLoop.addBombRate(1);
            }
        }

    }

    @Override
    public void kill() {
        if (!_alive) return;
        _alive = false;
    }

    @Override
    protected void afterKill() {
        if (_timeAfter > 0) --_timeAfter;
        else {
            _Game_board.endGame();
        }
    }

    @Override
    protected void calculateMove() {
        int xa = 0, ya = 0;
		if(_input.up) ya--;
		if(_input.down) ya++;
		if(_input.left) xa--;
		if(_input.right) xa++;
		
		if(xa != 0 || ya != 0)  {
			move(xa * GameLoop.getBomberSpeed(), ya * GameLoop.getBomberSpeed());
			_moving = true;
		} else {
			_moving = false;
		}
    }

    @Override
    public boolean canMove(double x, double y) {
       for (int c = 0; c < 4; c++) { //colision detection for each corner of the player
			double xt = ((_x + x) + c % 2 * 9) / GameLoop.TILES_SIZE; //divide with tiles size to pass to tile coordinate
			double yt = ((_y + y) + c / 2 * 10 - 13) / GameLoop.TILES_SIZE; //these values are the best from multiple tests
			
			Entity a = _Game_board.getEntity(xt, yt, this);
			
			if(!a.collide(this))
				return false;
		}
		
		return true;
        //return false;
    }

    @Override
    public void move(double xa, double ya) {
        if(xa > 0) _direction = 1;
		if(xa < 0) _direction = 3;
		if(ya > 0) _direction = 2;
		if(ya < 0) _direction = 0;
		
		if(canMove(0, ya)) { //separate the moves for the player can slide when is colliding
			_y += ya;
		}
		
		if(canMove(xa, 0)) {
			_x += xa;
		}
    }

    @Override
    public boolean collide(Entity e) {
        if(e instanceof Flame){
            this.kill();
            return false;
        }
        if(e instanceof Enemy){
            this.kill();
            return true;
        }
        if( e instanceof LayerEntity) return(e.collide(this));
        return true;
    }

    //sprite
    private void chooseSprite() {
        switch (_direction) {
            case 0:
                _sprite = Sprite.player_up;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, _animate, 20);
                }
                break;
            case 1:
                _sprite = Sprite.player_right;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, _animate, 20);
                }
                break;
            case 2:
                _sprite = Sprite.player_down;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, _animate, 20);
                }
                break;
            case 3:
                _sprite = Sprite.player_left;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, _animate, 20);
                }
                break;
            default:
                _sprite = Sprite.player_right;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, _animate, 20);
                }
                break;
        }
    }
}
