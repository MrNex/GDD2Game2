package state.enginestates;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import mathematics.Vec;
import engine.Engine;
import engine.manager.CameraManager;
import engine.manager.ContentManager;
import engine.manager.InputManager;
import objects.GameObject;
import objects.MovableGameObject;
import state.objectstates.PlayerAimState;
import state.objectstates.TailState;

/**
 * A state which defines the behavior of the engine while inside of a level playing the game.
 * @author Nex
 *
 */
public class GameState extends EngineState {

	//Attributes
	private int currentLevel;
	private MovableGameObject player;

	//Accessors / Modifiers
	/**
	 * Gets the instance of the player
	 * @return 
	 */
	public MovableGameObject getPlayer(){
		return player;
	}

	/**
	 * Constructs a gamestate
	 */
	public GameState() {
		super();
	}


	/**
	 * Initializes this state of the engine
	 */
	public void init(){
		super.init();
		//Set the current level
		currentLevel = 0;

		//Get reference to content manager
		ContentManager content = (ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER);

		//Create the player
		player = new MovableGameObject(160, 60, 20, 20, new Vec(1, 0));

		//Set image
		player.setImage(content.getImage("ratImage"));
		player.setVisible(true);

		//Set state
		player.setState(new PlayerAimState());

		//Set trigger
		player.setTriggerable(true);

		//Set camera to follow player
		((CameraManager)Engine.currentInstance.getManager(Engine.Managers.CAMERAMANAGER)).setFollow(player);

	}

	/**
	 * Calls base class update
	 * Queries input manager for any input which alters the state of the game
	 * Special input keys are as follows
	 * 		r: resets the game
	 */
	@Override
	public void update(){
		super.update();

		//Get reference to input manager
		InputManager input = (InputManager)Engine.currentInstance.getManager(Engine.Managers.INPUTMANAGER);

		if(input.isKeyPressed('r')){
			resetGame();
		}
	}

	/**
	 * Loads the next level
	 * Wipes all objects from the current state
	 * Increments the current level
	 * Loads the new current level
	 * Adds the player to the state 
	 */
	public void loadNextLevel(){
		//Get reference to content manager
		ContentManager content = (ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER);

		//Clear objects in state
		wipeState();

		//Increment current level
		currentLevel++;

		//Wipe the players memory of all checkpoints
		player.setActiveCheckpoint(null);

		//Load the next level
		content.getLevel("TestLevel" + currentLevel).load();


		//Create the player's tail	
		ArrayList<GameObject> tail = new ArrayList<GameObject>();
		tail.add( new GameObject(player.getXPos(), player.getYPos(), 5, 5, new Vec(1, 0)));
		tail.get(0).setShape(TailState.ellipse, Color.orange);
		tail.get(0).setVisible(true);
		tail.get(0).setSolid(false);
		tail.get(0).setState(new TailState(player));
		
		for(int i = 1; i < 10; i++){
			tail.add(new GameObject(player.getXPos(), player.getYPos(), 5, 5, new Vec(1, 0)));
			tail.get(i).setShape(TailState.ellipse, Color.orange);
			tail.get(i).setVisible(true);
			tail.get(i).setSolid(false);
			tail.get(i).setState(new TailState(tail.get(i - 1)));
		}
		
		
		//Add player
		addObj(player);
		for(int i = 0; i < 10; i++){
			addObj(tail.get(i));
		}
	}

	/**
	 * Resets the game state
	 */
	private void resetGame(){
		currentLevel = 0;
		player.setState(new PlayerAimState());
		loadNextLevel();
	}

}
