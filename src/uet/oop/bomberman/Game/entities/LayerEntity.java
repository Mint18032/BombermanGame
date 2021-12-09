package uet.oop.bomberman.Game.entities;

import uet.oop.bomberman.Game.entities.MapObjects.DestroyableObject.DestroyableObject;
import uet.oop.bomberman.graphics.Screen;

import java.util.LinkedList;


/**
 * Chứa nhiều Entity trên cùng một vị trí.
 */
public class LayerEntity extends Entity {
	
	protected LinkedList<Entity> _entities = new LinkedList<>();
	
	public LayerEntity(int x, int y, Entity ... entities) {
		_x = x;
		_y = y;
		
		for (int i = 0; i < entities.length; i++) {
			_entities.add(entities[i]); 
			
			if(i > 1) {
				if(entities[i] instanceof DestroyableObject)
					((DestroyableObject)entities[i]).addBelowSprite(entities[i-1].getSprite());
			}
		}
	}
	
	@Override
	public void update() {
		clearRemoved();
		getTopEntity().update();
	}
	
	@Override
	public void render(Screen screen) {
		getTopEntity().render(screen);
	}
	
	public Entity getTopEntity() {
		
		return _entities.getLast();
	}
	
	private void clearRemoved() {
		Entity top  = getTopEntity();
		
		if(top.isRemoved())  {
			_entities.removeLast();
		}
	}
	
	public void addBeforeTop(Entity e) {
		_entities.add(_entities.size() - 1, e);
	}
	
	@Override
	public boolean collide(Entity e) {
                
		return getTopEntity().collide(e);
	}

}
