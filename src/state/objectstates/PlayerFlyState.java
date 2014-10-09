package state.objectstates;

import java.awt.Graphics2D;

import mathematics.Vec;
import objects.GameObject;
import objects.MovableGameObject;
import triggers.Trigger;
import engine.Engine;
import engine.Engine.Managers;
import engine.manager.InputManager;

/**
 * The player is made up of a finite state machine of two states
 * {@link PlayerAimState} and playerFlyState.
 * 
 * PlayerFlyState makes the attached gameObject fly in the direction of it's forward vector
 * Allowing resisted single-axis steering using the mouse.
 * 
 * 
 * Attaches triggers to the gameobject that will switch the state back to PlayerAimState upon
 * colliding with another gameObject
 * @author Nex
 *
 */
public class PlayerFlyState extends ObjectState{

	private double movementSpeed;
	
	public PlayerFlyState() {
		movementSpeed = 0.005;
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
		attachedTo.addTrigger(new Trigger(){

			@Override
			public void action(GameObject triggeredBy) {
				attachedTo.setState(new PlayerAimState());
				attachedTo.removeTrigger(this);
				
			}
			
		});
		
	}

	/**
	 * Creates a translation vector in the direction of the attached
	 * GAmeobjects forward vector, scales it by the movemet speed,
	 * and moves the object.
	 */
	@Override
	public void update() {
		//Create translationVector as copy of forward vector
		Vec tVec = new Vec(2);
		tVec.copy(getPlayer().getForward());
		
		//Scale by movement speed
		tVec.setMag(movementSpeed);
		
		//Move
		getPlayer().move(tVec);
		//drag dat shape along.
		attachedTo.updateShape();
	}

	@Override
	public void drawEffects(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

}
