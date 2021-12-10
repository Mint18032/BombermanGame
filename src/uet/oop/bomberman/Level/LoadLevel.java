package uet.oop.bomberman.Level;

import uet.oop.bomberman.GameBoard;
import uet.oop.bomberman.GameExeption.LoadLevelException;

/**
 * Load và lưu trữ thông tin bản đồ các màn chơi
 */
public abstract class LoadLevel {

	protected int _width = 20, _height = 20;
	protected int _level;
	protected static final int _max_level = 2;
	protected GameBoard _Game_board;

	public LoadLevel(GameBoard gameBoard, int level) throws LoadLevelException {
		_Game_board = gameBoard;
		loadLevel(level);
	}

		public abstract void loadLevel(int level) throws LoadLevelException;

		public abstract void createEntities();

		public int getWidth() {
			return _width;
		}

		public int getHeight() {
			return _height;
		}

		public int getLevel() {
			return _level;
		}

		public boolean isMaxLevel() {
			return _max_level == _level;
		}

}
