package uet.oop.bomberman;

import sun.awt.image.DataBufferNative;
import uet.oop.bomberman.Input.InputKeyboard;
import uet.oop.bomberman.graphics.Screen;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class GameLoop extends Canvas {
    public static final int TILES_SIZE = 16,
            WIDTH = TILES_SIZE * (31 / 2),
            HEIGHT = 13 * TILES_SIZE;

    public static int SCALE = 3;

    public static final String GAMETITLE ="";

    private static final int BOMBRATE = 1;
    private static int bombRate = BOMBRATE;
    private static final int BOMBRADIUS = 1;
    private static int bombRadius = BOMBRADIUS;
    //Bomber Speed
    private static final double BOMBERSPEED = 1.0;
    private static double bomberSpeed = BOMBERSPEED;
    public static final int TIME = 200;
    public static final int POINTS = 0;
    protected static int SCREENDELAY = 3;

    protected int _screenDelay = SCREENDELAY;
    private GameBoard gameBoard;
    private Screen screen;
    private Frame _frame;

    private InputKeyboard input;
    private boolean running = false;
    private boolean paused = true;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels =((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    //Set up for a game
    public GameLoop(Frame frame) {
        _frame = frame;
        _frame.setTitle(GAMETITLE);
        screen = new Screen(WIDTH, HEIGHT);
        input = new InputKeyboard();
        gameBoard = new GameBoard(this,input,screen);
        addKeyListener(input);
    }

    //Update GameBoard ang input
    private void update() {
        input.update();
        gameBoard.update();
    }

    public void start() {
        running = true;
        int frames = 0;
        int updates = 0;
        long  lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0; //nanosecond, 60 frames per second
        double delta = 0;
        requestFocus();
        while (running) {
            long now = System.nanoTime();
            delta +=(now - lastTime)/ns;
            lastTime = now;
            while(delta >= 1) {
                update();
                updates++;
                delta--;
            }

            if(paused) {
                if(_screenDelay <= 0) {
                    gameBoard.setShow(-1);
                    paused = false;
                }

                renderScreen();
            } else {
                renderGame();
            }

            frames++;
            if(System.currentTimeMillis() - timer > 1000) {
                _frame.setTime(gameBoard.subtractTime());
                _frame.setPoints(gameBoard.getPoints());
                timer += 1000;
                _frame.setTitle(GAMETITLE);
                updates = 0;
                frames = 0;

                if(gameBoard.getShow() == 2)
                    --_screenDelay;
            }
        }
    }

    //Render Game
    private void renderGame() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();

        gameBoard.render(screen);

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }

    //Render Screen
    private void renderScreen() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();

        Graphics g = bs.getDrawGraphics();

        gameBoard.drawScreen(g);

        g.dispose();
        bs.show();
    }

    //Setter and Getter
    public GameBoard getGameBoard() {
        return this.gameBoard;
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

    public boolean isPaused() {
        return paused;
    }

    public void pause() {
        paused = true;
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
