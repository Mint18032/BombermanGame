package uet.oop.bomberman.Game.entities.Bomb;

import uet.oop.bomberman.GameBoard;
import uet.oop.bomberman.Game.entities.Entity;
import uet.oop.bomberman.Game.entities.Characters.Bomber;
import uet.oop.bomberman.Game.entities.Characters.Enemy.Enemy;
import uet.oop.bomberman.graphics.Screen;

/**
 * Flame: mô phỏng các vết bom nổ.
 */
public class Flame extends Entity {

	protected GameBoard _Game_board;
	protected int _direction;
	private int _radius;
	protected int xOrigin, yOrigin;
	protected FlameSegment[] _flameSegments = new FlameSegment[0];

	public Flame(int x, int y, int direction, int radius, GameBoard gameBoard) {
		xOrigin = x;
		yOrigin = y;
		_x = x;
		_y = y;
		_direction = direction;
		_radius = radius;
		_Game_board = gameBoard;
		createFlameSegments();
	}

	/**
	 * Tạo các FlameSegment, mỗi segment ứng một đơn vị độ dài.
	 */
	private void createFlameSegments() {
//		Tính toán độ dài Flame, tương ứng với số lượng segment
		_flameSegments = new FlameSegment[calculatePermitedDistance()];

		boolean last = false; // Đánh dấu segment cuối cùng.
		
		int x = (int)_x;
		int y = (int)_y;
		for (int i = 0; i < _flameSegments.length; i++) {
			last = i == _flameSegments.length -1 ? true : false;
			
			switch (_direction) {
				case 0: y--; break;
				case 1: x++; break;
				case 2: y++; break;
				case 3: x--; break;
			}
			_flameSegments[i] = new FlameSegment(x, y, _direction, last);
		}
	}

	/**
	 * Tính toán độ dài của Flame, nếu gặp vật cản là Brick/Wall, độ dài sẽ bị cắt ngắn.
	 */
	private int calculatePermitedDistance() {
		int radius = 0;
		int x = (int)_x;
		int y = (int)_y;
		while(radius < _radius) {
			if(_direction == 0) y--;
			if(_direction == 1) x++;
			if(_direction == 2) y++;
			if(_direction == 3) x--;
			
			Entity a = _Game_board.getEntity(x, y, null);
			
			if(a instanceof Bomb) ++radius; // Nổ phải ở dưới bom.
			
			if(a.collide(this) == false) // Không thể đi qua.
				break;
			
			++radius;
		}
		return radius;
	}

	/**
	 * Xác định vị trí các bộ phận nổ.
	 */
	public FlameSegment flameSegmentAt(int x, int y) {
		for (int i = 0; i < _flameSegments.length; i++) {
			if(_flameSegments[i].getX() == x && _flameSegments[i].getY() == y)
				return _flameSegments[i];
		}
		return null;
	}

	@Override
	public void update() {}
	
	@Override
	public void render(Screen screen) {
		for (int i = 0; i < _flameSegments.length; i++) {
			_flameSegments[i].render(screen);
		}
	}

	/**
	 * Xử lý va chạm.
	 */
	@Override
	public boolean collide(Entity e) {
		if(e instanceof Bomber) ((Bomber) e).kill();
		if(e instanceof Enemy) ((Enemy) e).kill();
		return true;
	}
}
