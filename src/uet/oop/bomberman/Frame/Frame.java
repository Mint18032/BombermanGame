package uet.oop.bomberman.Frame;

import uet.oop.bomberman.GameLoop;

import javax.swing.*;
import java.awt.*;

/**
 * Frame riêng cho game.
 */
public class Frame extends JFrame {
	
	public GamePanel _gamepane;
	private JPanel _containerpane;
	private NortificationPanel _infopanel;
	
	private GameLoop _gameLoop;

	public Frame() {
		_containerpane = new JPanel(new BorderLayout());
		_gamepane = new GamePanel(this);
		_infopanel = new NortificationPanel(_gamepane.getGame());

		_containerpane.add(_infopanel, BorderLayout.PAGE_START);
		_containerpane.add(_gamepane, BorderLayout.PAGE_END);

		_gameLoop = _gamepane.getGame();
		add(_containerpane);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		_gameLoop.start();
	}
	
	public void setTime(int time) {
		_infopanel.setTime(time);
	}
	
	public void setPoints(int points) {
		_infopanel.setPoints(points);
	}

}
