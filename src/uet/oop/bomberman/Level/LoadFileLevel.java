package uet.oop.bomberman.Level;

import uet.oop.bomberman.Game.entities.Characters.Bomber;
import uet.oop.bomberman.Game.entities.Characters.Enemy.Balloom;
import uet.oop.bomberman.Game.entities.Characters.Enemy.Oneal;
import uet.oop.bomberman.Game.entities.LayerEntity;
import uet.oop.bomberman.Game.entities.MapObjects.DestroyableObject.Brick;
import uet.oop.bomberman.Game.entities.MapObjects.Grass;
import uet.oop.bomberman.Game.entities.MapObjects.Item.BombItem;
import uet.oop.bomberman.Game.entities.MapObjects.Item.FlameItem;
import uet.oop.bomberman.Game.entities.MapObjects.Item.SpeedItem;
import uet.oop.bomberman.Game.entities.MapObjects.Portal;
import uet.oop.bomberman.Game.entities.MapObjects.Wall;
import uet.oop.bomberman.GameBoard;
import uet.oop.bomberman.GameLoop;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LoadFileLevel extends LoadLevel {

    private static char[][] levelMap;

    public LoadFileLevel(GameBoard gameboard, int level) {
        super(gameboard, level);
    }
    @Override
    public void loadLevel(int levelNumber) {
        List<String> list = new ArrayList<>();
        try {
            FileReader fr = new FileReader("res\\levels\\Level" + levelNumber + ".txt");//doc tep luu map
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (!line.equals("")) {
                list.add(line);
                line = br.readLine();
                //doc file txt luu vao list
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] arrays = list.get(0).trim().split(" ");
        level = Integer.parseInt(arrays[0]);
        height = Integer.parseInt(arrays[1]);
        width = Integer.parseInt(arrays[2]);
        levelMap = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                levelMap[i][j] = list.get(i + 1).charAt(j);
            }
        }
    }

    @Override
    public void createEntities() {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                int pos = x + y * getWidth();
                char c = levelMap[y][x];
                switch (c) {
                    case ' ':
                        gameboard.addEntity(pos, new Grass(x, y, Sprite.grass));
                        break;
                    case '#':
                        gameboard.addEntity(pos, new Wall(x, y, Sprite.wall));
                        break;
                    case 'x':
                        gameboard.addEntity(pos, new LayerEntity(x, y,
                                new Grass(x, y, Sprite.grass),
                                new Portal(x, y, gameboard, Sprite.portal),
                                new Brick(x, y, Sprite.brick)));
                        break;
                    case '*':
                        gameboard.addEntity(x + y * width,
                                new LayerEntity(x, y,
                                        new Grass(x, y, Sprite.grass),
                                        new Brick(x, y, Sprite.brick)
                                )
                        );
                        break;
                    case 'p':
                        gameboard.addCharacter(new Bomber(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + GameLoop.TILES_SIZE, gameboard));
                        Screen.setOffset(0, 0);
                        gameboard.addEntity(x + y * width, new Grass(x, y, Sprite.grass));
                        break;
                    case '1':
                        gameboard.addCharacter(new Balloom(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + GameLoop.TILES_SIZE, gameboard));
                        _board.addEntity(x + y * width, new Grass(x, y, Sprite.grass));
                        break;
                    case '2':
                        gameboard.addCharacter(new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + GameLoop.TILES_SIZE, gameboard));
                        gameboard.addEntity(pos, new Grass(x, y, Sprite.grass));
                        break;
                    case '3':
                        gameboard.addCharacter(new Doll(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + GameLoop.TILES_SIZE, gameboard));
                        gameboard.addEntity(x + y * width, new Grass(x, y, Sprite.grass));
                        break;
                    case 'b':
                        LayerEntity layer = new LayerEntity(x, y,
                                new Grass(x, y, Sprite.grass),
                                new BombItem(x, y, Sprite.powerup_bombs),
                                new Brick(x, y, Sprite.brick));
                        gameboard.addEntity(pos, layer);
                        break;
                    // Thêm SpeedItem
                    case 's':
                        layer = new LayerEntity(x, y,
                                new Grass(x, y, Sprite.grass),
                                new SpeedItem(x, y, Sprite.powerup_speed),
                                new Brick(x, y, Sprite.brick));
                        gameboard.addEntity(pos, layer);
                        break;
                    // Thêm FlameItem
                    case 'f':
                        layer = new LayerEntity(x, y,
                                new Grass(x, y, Sprite.grass),
                                new FlameItem(x, y, Sprite.powerup_flames),
                                new Brick(x, y, Sprite.brick));
                        gameboard.addEntity(pos, layer);
                        break;

                    default:
                        gameboard.addEntity(pos, new Grass(x, y, Sprite.grass));
                        break;

                }
            }
        }
    }
}
