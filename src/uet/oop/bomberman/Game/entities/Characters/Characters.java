package uet.oop.bomberman.Game.entities.Characters;

import uet.oop.bomberman.GameBoard;
import uet.oop.bomberman.GameLoop;
import uet.oop.bomberman.Game.entities.Animation;
import uet.oop.bomberman.graphics.Screen;

/**
 * Nhân vật (bao gồm Bomber và Enemy).
 */
public abstract class Characters extends Animation {
	
	protected GameBoard _Game_board;
	protected int _direction = -1;
	protected boolean _alive = true;
	protected boolean _moving = false;
	public int _timeAfter = 40;
	
	public Characters(int x, int y, GameBoard gameBoard) {
		_x = x;
		_y = y;
		_Game_board = gameBoard;
	}
	
	@Override
	public abstract void update();
	
	@Override
	public abstract void render(Screen screen);

	/**
	 * Tính toán hướng đi
	 */
	protected abstract void calculateMove();
	
	protected abstract void move(double xa, double ya);

	/**
	 * Được gọi khi đối tượng bị tiêu diệt.
	 */
	public abstract void kill();

	/**
	 * Xử lý hiệu ứng bị tiêu diệt.
	 */
	protected abstract void afterKill();

	/**
	 * Xác định bước đi tới vị trí x, y có hợp lệ hay không.
	 */
	protected abstract boolean canMove(double x, double y);

	protected double getXMessage() {
		return (_x * GameLoop.SCALE) + (_sprite.SIZE / 2 * GameLoop.SCALE);
	}
	
	protected double getYMessage() {
		return (_y* GameLoop.SCALE) - (_sprite.SIZE / 2 * GameLoop.SCALE);
	}
	
}
