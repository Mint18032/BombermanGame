package uet.oop.bomberman;

import uet.oop.bomberman.Game.entities.Entity;
import uet.oop.bomberman.Game.entities.Nortification;
import uet.oop.bomberman.Game.entities.Bomb.Bomb;
import uet.oop.bomberman.Game.entities.Bomb.FlameSegment;
import uet.oop.bomberman.Game.entities.Characters.Bomber;
import uet.oop.bomberman.Game.entities.Characters.Characters;
import uet.oop.bomberman.GameExeption.LoadLevelException;
import uet.oop.bomberman.Level.LoadFileLevel;
import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.graphics.Render;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.Input.InputKeyboard;
import uet.oop.bomberman.Level.LoadLevel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Quản lý thao tác điều khiển, load level, render các màn hình của game
 */
public class GameBoard implements Render {
	protected LoadLevel _Load_level;
	protected GameLoop _gameLoop;
	protected InputKeyboard _input;
	protected Screen _screen;
	
	public Entity[] _entities;
	public List<Characters> _characters = new ArrayList<>();
	protected List<Bomb> _bombs = new ArrayList<>();
	private List<Nortification> _nortifications = new ArrayList<>();
	
	private int _screenToShow = -1; //1:endgame, 2:changelevel, 3:paused
	
	private int _time = GameLoop.TIME;
	private int _points = GameLoop.POINTS;
	
	public GameBoard(GameLoop gameLoop, InputKeyboard input, Screen screen) {
		_gameLoop = gameLoop;
		_input = input;
		_screen = screen;
		
		loadLevel(1); //Bắt đầu từ level 1
	}
	
	@Override
	public void update() {
		if( _gameLoop.isPaused() ) return;
		
		updateEntities();
		updateCharacters();
		updateBombs();
		updateMessages();
		detectEndGame();
		
		for (int i = 0; i < _characters.size(); i++) {
			Characters a = _characters.get(i);
			if(a.isRemoved()) _characters.remove(i);
		}
	}

	//Cập nhật các đối tượng trong danh sách.
	protected void updateEntities() {
		if( _gameLoop.isPaused() ) return;
		for (int i = 0; i < _entities.length; i++) {
			_entities[i].update();
		}
	}

	//Cập nhật nhân vật.
	protected void updateCharacters() {
		if( _gameLoop.isPaused() ) return;
		Iterator<Characters> itr = _characters.iterator();

		while(itr.hasNext() && !_gameLoop.isPaused())
			itr.next().update();
	}

	//Cập nhật Bom.
	protected void updateBombs() {
		if( _gameLoop.isPaused() ) return;
		Iterator<Bomb> itr = _bombs.iterator();

		while(itr.hasNext())
			itr.next().update();
	}

	//Cập nhật thông báo thêm điểm sao khi giết Ennemy.
	protected void updateMessages() {
		if( _gameLoop.isPaused() ) return;
		Nortification m;
		int left;
		for (int i = 0; i < _nortifications.size(); i++) {
			m = _nortifications.get(i);
			left = m.getDuration();

			if(left > 0)
				m.setDuration(--left);
			else
				_nortifications.remove(i);
		}
	}

	@Override
	public void render(Screen screen) {
		if( _gameLoop.isPaused() ) return;
		
		//Chỉ render trong phần màn hình có thể thấy được
		int x0 = Screen.xOffset >> 4; //tile precision, -> left X
		int x1 = (Screen.xOffset + screen.getWidth() + GameLoop.TILES_SIZE) / GameLoop.TILES_SIZE; // -> right X
		int y0 = Screen.yOffset >> 4;
		int y1 = (Screen.yOffset + screen.getHeight()) / GameLoop.TILES_SIZE; //Render thêm cột mỗi lần vượt màn hình
		
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				_entities[x + y * _Load_level.getWidth()].render(screen);
			}
		}
		
		renderBombs(screen);
		renderCharacter(screen);
		
	}

	//Khởi tạo Nhân vật.
	protected void renderCharacter(Screen screen) {
		Iterator<Characters> itr = _characters.iterator();

		while(itr.hasNext())
			itr.next().render(screen);
	}

	//Khởi tạo Bom.
	protected void renderBombs(Screen screen) {
		Iterator<Bomb> itr = _bombs.iterator();

		while(itr.hasNext())
			itr.next().render(screen);
	}

	//Khởi tạo thông báo trên màn hình.
	public void renderMessages(Graphics g) {
		Nortification m;
		for (int i = 0; i < _nortifications.size(); i++) {
			m = _nortifications.get(i);

			g.setFont(new Font("Arial", Font.PLAIN, m.getSize()));
			g.setColor(m.getColor());
			g.drawString(m.getMessage(), (int)m.getX() - Screen.xOffset  * GameLoop.SCALE, (int)m.getY());
		}
	}

	//Xử lí level
	public void nextLevel() {
		GameLoop.setBombRadius(1);
		GameLoop.setBombRate(1);
		GameLoop.setBomberSpeed(1.0);
		loadLevel(_Load_level.getLevel() + 1);
	}
	
	public void loadLevel(int level) {
		_time = GameLoop.TIME;
		_screenToShow = 2;
		_gameLoop.resetScreenDelay();
		_gameLoop.pause();
		_characters.clear();
		_bombs.clear();
		_nortifications.clear();
		
		try {
			_Load_level = new LoadFileLevel(this, level);
			_entities = new Entity[_Load_level.getHeight() * _Load_level.getWidth()];
			
			_Load_level.createEntities();
		} catch (LoadLevelException e) {
			endGame();
		}
	}

	//Hết thời gian -> endGame()
	protected void detectEndGame() {
		if(_time <= 0)
			endGame();
	}

	public void endGame() {
		_screenToShow = 1;
		_gameLoop.resetScreenDelay();
		Sound end = new Sound("gameOver");
		end.play();
		_gameLoop.pause();
	}

	public boolean detectNoEnemies() {// Phát hiện Enemy trên sân
		int total = 0;
		for (int i = 0; i < _characters.size(); i++) {
			if(_characters.get(i) instanceof Bomber == false)
				++total;
		}
		
		return total == 0;
	}
	
	public void drawScreen(Graphics g) {
		switch (_screenToShow) {
			case 1:
				_screen.drawEndGame(g, _points);
				break;
			case 2:
				_screen.drawChangeLevel(g, _Load_level.getLevel());
				break;
			case 3:
				_screen.drawPaused(g);
				break;
		}
	}

	//Xác định vị trí các phần tử như bom và vụ nổ
	public Entity getEntity(double x, double y, Characters m) {
		
		Entity res = null;
		
		res = getFlameSegmentAt((int)x, (int)y);
		if( res != null) return res;
		
		res = getBombAt(x, y);
		if( res != null) return res;
		
		res = getCharacterAtExcluding((int)x, (int)y, m);
		if( res != null) return res;
		
		res = getEntityAt((int)x, (int)y);
		
		return res;
	}
	
	public List<Bomb> getBombs() {
		return _bombs;
	}

	//Xác định vị trí Bom.
	public Bomb getBombAt(double x, double y) {
		Iterator<Bomb> bs = _bombs.iterator();
		Bomb b;
		while(bs.hasNext()) {
			b = bs.next();
			if(b.getX() == (int)x && b.getY() == (int)y)
				return b;
		}
		
		return null;
	}

	public Bomber getBomber() {
		Iterator<Characters> itr = _characters.iterator();
		
		Characters cur;
		while(itr.hasNext()) {
			cur = itr.next();
			
			if(cur instanceof Bomber)
				return (Bomber) cur;
		}
		
		return null;
	}

	//Xác định nhân vật được loại bỏ tại vị trí.
	public Characters getCharacterAtExcluding(int x, int y, Characters a) {
		Iterator<Characters> itr = _characters.iterator();
		
		Characters cur;
		while(itr.hasNext()) {
			cur = itr.next();
			if(cur == a) {
				continue;
			}
			
			if(cur.getXTile() == x && cur.getYTile() == y) {
				return cur;
			}
				
		}
		
		return null;
	}

	//Xác định vị trí của vụ nổ.
	public FlameSegment getFlameSegmentAt(int x, int y) {
		Iterator<Bomb> bs = _bombs.iterator();
		Bomb b;
		while(bs.hasNext()) {
			b = bs.next();
			
			FlameSegment e = b.flameAt(x, y);
			if(e != null) {
				return e;
			}
		}
		
		return null;
	}

	//Xác định thực thể tại vị trí.
	public Entity getEntityAt(double x, double y) {
		return _entities[(int)x + (int)y * _Load_level.getWidth()];
	}
	
	public void addEntity(int pos, Entity e) {
		_entities[pos] = e;
	}
	
	public void addCharacter(Characters e) {
		_characters.add(e);
	}
	
	public void addBomb(Bomb e) {
		_bombs.add(e);
	}
	
	public void addMessage(Nortification e) {
		_nortifications.add(e);
	}


	


	public int subtractTime() {
		if(_gameLoop.isPaused())
			return this._time;
		else
			return this._time--;
	}

	public InputKeyboard getInput() {
		return _input;
	}

	public LoadLevel getLevel() {
		return _Load_level;
	}

	public GameLoop getGame() {
		return _gameLoop;
	}

	public int getShow() {
		return _screenToShow;
	}

	public void setShow(int i) {
		_screenToShow = i;
	}

	public int getTime() {
		return _time;
	}

	public int getPoints() {
		return _points;
	}

	public void addPoints(int points) {
		this._points += points;
	}
	
	public int getWidth() {
		return _Load_level.getWidth();
	}

	public int getHeight() {
		return _Load_level.getHeight();
	}
	
}
