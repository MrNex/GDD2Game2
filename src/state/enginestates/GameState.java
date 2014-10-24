package state.enginestates;

import mathematics.Vec;
import engine.Engine;
import engine.manager.CameraManager;
import engine.manager.ContentManager;
import engine.manager.InputManager;
import objects.GameObject;
import objects.MovableGameObject;
import state.objectstates.PlayerAimState;

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



		//Add player
		addObj(player);
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
