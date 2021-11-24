package uet.oop.bomberman.Frame;

import uet.oop.bomberman.GameLoop;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private GameLoop gameLoop;

    public GamePanel (Frame _frame) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(GameLoop.WIDTH*GameLoop.SCALE, GameLoop.HEIGHT*GameLoop.SCALE));

        gameLoop = new GameLoop(_frame);

        add(gameLoop);
        setVisible(true);
        setFocusable(true);
    }

    public GameLoop getGame() {
        return gameLoop;
    }
}
