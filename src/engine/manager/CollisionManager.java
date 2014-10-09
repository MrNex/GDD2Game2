package engine.manager;

import objects.GameObject;
import objects.MovableGameObject;
import engine.Engine;

/**
 * CollisionManager will tap into the current list of objects
 * and query any movableGameObject if it has intersected with any other gameobject.
 * If a collision returns true, it is resolved by reverting the movableGameObject
 * to it's previousPosition before the collision took place.
 * 
 * @author Nex
 *
 */
public class CollisionManager extends Manager {

	/**
	 * Constructs collision manager
	 */
	public CollisionManager() {
		super();
	}

	/**
	 * Initializes member variables
	 */
	@Override
	public void init() {

	}

	/**
	 * Iterates through the list of objects in the current state and 
	 * determines if any movablegameobject is colliding with any other
	 * gameobject. 
	 * 
	 *  Upon detecting a collision it is resolved by reverting the 
	 *  movablegameobject back to it's previousPosition, before the collision took place.
	 */
	@Override
	public void update() {
		for(GameObject obj1 : Engine.currentInstance.getCurrentState().getObjList()){
			if(obj1 instanceof MovableGameObject){
				for(GameObject obj2 : Engine.currentInstance.getCurrentState().getObjList()){
					if(obj1 == obj2) continue;
					if(obj1.isColliding(obj2)) 
						((MovableGameObject)obj1).revert();
				}
			}
		}
	}

}
