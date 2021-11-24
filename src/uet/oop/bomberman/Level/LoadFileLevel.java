package uet.oop.bomberman.Level;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import uet.oop.bomberman.GameBoard;
import uet.oop.bomberman.GameLoop;
import uet.oop.bomberman.Game.entities.LayerEntity;
import uet.oop.bomberman.Game.entities.Characters.Bomber;
import uet.oop.bomberman.Game.entities.Characters.Enemy.Balloom;
import uet.oop.bomberman.Game.entities.Characters.Enemy.Doll;
import uet.oop.bomberman.Game.entities.Characters.Enemy.Oneal;
import uet.oop.bomberman.Game.entities.MapObjects.Grass;
import uet.oop.bomberman.Game.entities.MapObjects.Portal;
import uet.oop.bomberman.Game.entities.MapObjects.Wall;
import uet.oop.bomberman.Game.entities.MapObjects.DestroyableObject.Brick;
import uet.oop.bomberman.Game.entities.MapObjects.Item.BombItem;
import uet.oop.bomberman.Game.entities.MapObjects.Item.FlameItem;
import uet.oop.bomberman.Game.entities.MapObjects.Item.SpeedItem;
import uet.oop.bomberman.GameExeption.LoadLevelException;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

public class LoadFileLevel extends LoadLevel {

    /**
     * Ma trận chứa thông tin bản đồ, mỗi phần tử lưu giá trị kí tự đọc được từ
     * ma trận bản đồ trong tệp cấu hình
     */
    private static char[][] _map;

    public LoadFileLevel(GameBoard gameBoard, int level) throws LoadLevelException {
        super(gameBoard, level);
    }

    @Override
    public void loadLevel(int level) {
        List<String> list = new ArrayList<>();
        try {
            FileReader fr = new FileReader("res\\levels\\Level" + level + ".txt");//doc tep luu map
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
        _level = Integer.parseInt(arrays[0]);
        _height = Integer.parseInt(arrays[1]);
        _width = Integer.parseInt(arrays[2]);
        _map = new char[_height][_width];
        for (int i = 0; i < _height; i++) {
            for (int j = 0; j < _width; j++) {
                _map[i][j] = list.get(i + 1).charAt(j);
            }
        }
        //gan cac phan tu cho mang
    }

    @Override
    public void createEntities() {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                int pos = x + y * getWidth();
                char c = _map[y][x];
                switch (c) {
                    // Thêm grass
                    case ' ':
                        _Game_board.addEntity(pos, new Grass(x, y, Sprite.grass));
                        break;
                    // Thêm Wall
                    case '#':
                        _Game_board.addEntity(pos, new Wall(x, y, Sprite.wall));
                        break;
                    // Thêm Portal
                    case 'x':
                        _Game_board.addEntity(pos, new LayerEntity(x, y,
                                new Grass(x, y, Sprite.grass),
                                new Portal(x, y, _Game_board, Sprite.portal),
                                new Brick(x, y, Sprite.brick)));
                        break;
                    // Thêm brick
                    case '*':
                        _Game_board.addEntity(x + y * _width,
                                new LayerEntity(x, y,
                                        new Grass(x, y, Sprite.grass),
                                        new Brick(x, y, Sprite.brick)
                                )
                        );
                        break;
                    // Thêm Bomber
                    case 'p':
                        _Game_board.addCharacter(new Bomber(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + GameLoop.TILES_SIZE, _Game_board));
                        Screen.setOffset(0, 0);
                        _Game_board.addEntity(x + y * _width, new Grass(x, y, Sprite.grass));
                        break;

                    // Thêm balloon
                    case '1':
                        _Game_board.addCharacter(new Balloom(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + GameLoop.TILES_SIZE, _Game_board));
                        _Game_board.addEntity(x + y * _width, new Grass(x, y, Sprite.grass));
                        break;
                    // Thêm oneal
                    case '2':
                        _Game_board.addCharacter(new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + GameLoop.TILES_SIZE, _Game_board));
                        _Game_board.addEntity(pos, new Grass(x, y, Sprite.grass));
                        break;
                    // Thêm doll
                    case '3':
                        _Game_board.addCharacter(new Doll(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + GameLoop.TILES_SIZE, _Game_board));
                        _Game_board.addEntity(x + y * _width, new Grass(x, y, Sprite.grass));
                        break;
                    // Thêm oneal
                    // Thêm BomItem            
                    case 'b':
                        LayerEntity layer = new LayerEntity(x, y,
                                new Grass(x, y, Sprite.grass),
                                new BombItem(x, y, Sprite.powerup_bombs),
                                new Brick(x, y, Sprite.brick));
                        _Game_board.addEntity(pos, layer);
                        break;
                    // Thêm SpeedItem
                    case 's':
                        layer = new LayerEntity(x, y,
                                new Grass(x, y, Sprite.grass),
                                new SpeedItem(x, y, Sprite.powerup_speed),
                                new Brick(x, y, Sprite.brick));
                        _Game_board.addEntity(pos, layer);
                        break;
                    // Thêm FlameItem
                    case 'f':
                        layer = new LayerEntity(x, y,
                                new Grass(x, y, Sprite.grass),
                                new FlameItem(x, y, Sprite.powerup_flames),
                                new Brick(x, y, Sprite.brick));
                        _Game_board.addEntity(pos, layer);
                        break;

                    default:
                        _Game_board.addEntity(pos, new Grass(x, y, Sprite.grass));
                        break;

                }
            }
        }
    }
}
