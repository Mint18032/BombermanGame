package uet.oop.bomberman.Frame;

import uet.oop.bomberman.GameLoop;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private GameLoop gameLoop;

    public GamePanel (Frame frame) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(GameLoop.WIDTH*GameLoop.SCALE, GameLoop.HEIGHT*GameLoop.SCALE));

        gameLoop = new GameLoop(frame);

        add(gameLoop);
        setVisible(true);
        setFocusable(true);
    }

    public void getGame() {
        return gameLoop;
    }
}
