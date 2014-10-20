package levels;

import java.util.ArrayList;

import engine.Engine;
import objects.GameObject;
import state.enginestates.EngineState;

/**
 * Class which defines a level in the game
 * 
 * @author Nex
 *
 */
public class Level {

	//Attributes
	private ArrayList<GameObject> objects;
	
	//Accessors / Modifiers
	/**
	 * Get the list of objects
	 * @return The list of objects in this level
	 */
	public ArrayList<GameObject> getObjects(){
		return objects;
	}
	
	/**
	 * Add an object to the list of objects in this level
	 * @param objToAdd The object you want to add
	 */
	public void addObject(GameObject objToAdd){
		objects.add(objToAdd);
	}
	
	/**
	 * Constructs a level
	 */
	public Level() {
		init();
	}
	
	/**
	 * Initializes all member variables
	 */
	public void init() {
		objects = new ArrayList<GameObject>();
	}
	
	
	/**
	 * Loads a level's contents into the current state
	 */
	public void load(){
		EngineState currentState = Engine.currentInstance.getCurrentState();
		for(GameObject obj : objects){
			currentState.addObj(obj);
		}
	}

}
