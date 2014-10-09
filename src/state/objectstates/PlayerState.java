package state.objectstates;

import objects.GameObject;
import objects.MovableGameObject;
import engine.Engine;
import engine.Engine.Managers;
import engine.manager.InputManager;
import mathematics.Vec;

public class PlayerState extends ObjectState {

	//Atributes
	private double movementSpeed;
	
	public PlayerState() {
		// TODO Auto-generated constructor stub
		movementSpeed = 0.001;
	}

	/**
	 * Helper function so I don't need to keep casting attachedTo
	 * @return
	 */
	private MovableGameObject getPlayer(){
		return (MovableGameObject)attachedTo;
	}
	
	@Override
	public void enter() {
	}

	/**
	 * Allows any attached movablegameobject to move using wasd
	 */
	@Override
	public void update() {
		//Get the input manager
		InputManager input = (InputManager)Engine.currentInstance.getManager(Managers.INPUTMANAGER);
		
		//Get a vector with 2 components
		Vec translationVec = new Vec(2);
		
		//query
		if(input.isKeyPressed('W'))
			translationVec.incrementComponent(1, -movementSpeed);
		if(input.isKeyPressed('S'))
			translationVec.incrementComponent(1, movementSpeed);
		if(input.isKeyPressed('a'))
			translationVec.incrementComponent(0, -movementSpeed);
		if(input.isKeyPressed('d'))
			translationVec.incrementComponent(0, movementSpeed);
		
		//Actually Move
		getPlayer().move(translationVec);
		
		//Drag some light behind you
		attachedTo.updateShape();

	}
	

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

}
