package uet.oop.bomberman;

import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.Frame.Frame;
import uet.oop.bomberman.Input.InputKeyboard;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Tạo vòng lặp cho game, lưu trữ một vài tham số cấu hình toàn cục,
 * Gọi phương thức render(), update() cho tất cả các entity
 */
public class GameLoop extends Canvas {

    

	public static final int TILES_SIZE = 16,
							WIDTH = TILES_SIZE * (31 / 2),
							HEIGHT = 13 * TILES_SIZE;

	public static int SCALE = 3; //Độ phóng so với kích thước ban đầu.
	
	public static final String TITLE = "BOMBERMAN";
	
	private static final int BOMBRATE = 1;//Số lượng boom
	private static final int BOMBRADIUS = 1;//Độ lớn flame
	private static final double BOMBERSPEED = 1.0;//Tốc độ Bomber
	
	public static final int TIME = 400;
	public static final int POINTS = 0;
	
	protected static int SCREENDELAY = 3;

	protected static int bombRate = BOMBRATE;
	protected static int bombRadius = BOMBRADIUS;
	protected static double bomberSpeed = BOMBERSPEED;

	protected int _screenDelay = SCREENDELAY;
	
	private InputKeyboard _input;
	private boolean _running = false;
	private boolean _paused = true;
	
	private GameBoard _Game_board;
	private Screen screen;
	private Frame _frame;
	private Sound music;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	public GameLoop(Frame frame) {
		_frame = frame;
		_frame.setTitle(TITLE);
		
		screen = new Screen(WIDTH, HEIGHT);
		_input = new InputKeyboard();
		
		_Game_board = new GameBoard(this, _input, screen);
		addKeyListener(_input);
		music = new Sound("music");
		music.play();
	}

	/**
	 * Render GameBoard.
	 */
	private void renderGame() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		
		_Game_board.render(screen);
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen._pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		_Game_board.renderMessages(g);
		
		g.dispose();
		bs.show();
	}

	/**
	 * Render Graphics
	 */
	private void renderScreen() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		
		Graphics g = bs.getDrawGraphics();
		
		_Game_board.drawScreen(g);

		g.dispose();
		bs.show();
	}

	/**
	 * Update Game.
	 */
	private void update() {
		_input.update();

		if (_input.paused) {
			pauseGame();
		}

		if (_input.resume) {
			resume();
		}

		if (_input.mute) {
			if (!Sound.muted) {
				Sound.mute();
				music.stop();
			}
		} else if (_input.unmute) {
			Sound.unmute();
			music.play();
		}

		_Game_board.update();
	}

	/**
	 * Tính thời gian và fps theo thời gian thực.
	 * Render màn hình chơi.
	 */
	public void start() {
		_running = true;
		
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0; //nano giây, 60 frames trên giây
		double delta = 0;
		requestFocus();
		while(_running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				update();
				delta--;
			}

			if(_paused) {
				if(_screenDelay <= 0) {
					_Game_board.setShow(-1);
					_paused = false;
				}
				music.stop();
				renderScreen();
			} else {
				music.play();
				renderGame();
			}

			if(System.currentTimeMillis() - timer > 1000) {
				_frame.setTime(_Game_board.subtractTime()); //Xác định thời gian trong game
				_frame.setPoints(_Game_board.getPoints());
				timer += 1000;
				_frame.setTitle(TITLE);

				if(_Game_board.getShow() == 2)
					--_screenDelay;
			}
		}
	}
	
	public static double getBomberSpeed() {
		return bomberSpeed;
	}
	
	public static int getBombRate() {
		return bombRate;
	}
	
	public static int getBombRadius() {
		return bombRadius;
	}
	
	public static void addBomberSpeed(double i) {
		bomberSpeed += i;
	}
	
	public static void addBombRadius(int i) {
		bombRadius += i;
	}
	
	public static void addBombRate(int i) {
		bombRate += i;
	}

	public void resetScreenDelay() {
		_screenDelay = SCREENDELAY;
	}

	public GameBoard getBoard() {
		return _Game_board;
	}

	public boolean isPaused() {
		return _paused;
	}

	public void pause() {
		_paused = true;
	}

	public void pauseGame() {
		_Game_board.setShow(3);
		resetScreenDelay();
		_paused = true;
	}

	public void resume() {
		if(!_paused) return;
		_Game_board.setShow(-1);
		resetScreenDelay();
		_paused = false;
	}

	public static void setBombRate(int bombRate) {
        GameLoop.bombRate = bombRate;
    }

    public static void setBombRadius(int bombRadius) {
        GameLoop.bombRadius = bombRadius;
    }

    public static void setBomberSpeed(double bomberSpeed) {
        GameLoop.bomberSpeed = bomberSpeed;
    }
}
