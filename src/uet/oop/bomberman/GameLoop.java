package uet.oop.bomberman;

import java.awt.*;

public class GameLoop extends Canvas {
    public static final int TILES_SIZE = 16,
            WIDTH = TILES_SIZE * (31 / 2),
            HEIGHT = 13 * TILES_SIZE;

    public static int SCALE = 3;

    public static final String GAMETITLE ="";

    private static final int BOOMRATE = 1;
    private static final int BOOMRADIUS = 1;
    //Bomber Speed
    private static final double BOMBERSPEED = 1.0;
    public static final int TIME = 200;
    public static final int POINTS = 0;

    protected static int SCREENDELAY = 3;

}
