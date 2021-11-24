package uet.oop.bomberman.Frame;

import uet.oop.bomberman.GameLoop;

import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public GamePanel _gamepane;
    private JPanel containerpane;
    private NortificationPanel nortificationPanel;
    private GameLoop gameLoop;

    public Frame() {

        containerpane = new JPanel(new BorderLayout());
        _gamepane = new GamePanel(this);
        nortificationPanel = new NortificationPanel(_gamepane.getGame());

        containerpane.add(nortificationPanel, BorderLayout.PAGE_START);
        containerpane.add(_gamepane, BorderLayout.PAGE_END);

        gameLoop = _gamepane.getGame();
        add(containerpane);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        gameLoop.start();
    }

    public void setTime(int time) {
        nortificationPanel.setTime(time);
    }

    public void setPoints(int points) {
        nortificationPanel.setPoints(points);
    }

    private void renderMenu() {

    }
}
