package uet.oop.bomberman.Level;

import uet.oop.bomberman.GameLoop;

/**
 * Chuyển đỏi từ kích thước vật thể tương đương với mỗi ô là 1 đơn vị tọa độ.
 */
public class Coordinates {
	
	public static int pixelToTile(double i) {
		return (int)(i / GameLoop.TILES_SIZE);
	}
	
	public static int tileToPixel(int i) {
		return i * GameLoop.TILES_SIZE;
	}
	
	public static int tileToPixel(double i) {
		return (int)(i * GameLoop.TILES_SIZE);
	}
	
	
}
