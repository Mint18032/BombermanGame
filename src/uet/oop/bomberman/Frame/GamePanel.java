package uet.oop.bomberman.Frame;

import uet.oop.bomberman.GameLoop;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel {

	private GameLoop _gameLoop;
	
	public GamePanel(Frame frame) {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(GameLoop.WIDTH * GameLoop.SCALE, GameLoop.HEIGHT * GameLoop.SCALE));

		_gameLoop = new GameLoop(frame);

		add(_gameLoop);

		_gameLoop.setVisible(true);

		setVisible(true);
		setFocusable(true);
		
	}

	public GameLoop getGame() {
		return _gameLoop;
	}
	
}
