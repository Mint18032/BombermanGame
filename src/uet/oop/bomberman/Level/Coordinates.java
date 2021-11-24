package uet.oop.bomberman.Level;

import uet.oop.bomberman.GameLoop;

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
