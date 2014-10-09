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

	//Attributes
	private double movementSpeed;
	private double turnSpeed;
	
	
	public PlayerFlyState() {
		movementSpeed = 0.004;
		turnSpeed = 0.006;
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
		//Get a reference to the input manager
		InputManager input = (InputManager)Engine.currentInstance.getManager(Managers.INPUTMANAGER);

		//Create steering vector as copy of right vector
		Vec steeringVector = new Vec(getPlayer().getRight());

		//Find the difference in the previous and current mouse position on the x axis
		double xDiff = input.getMousePosition().getComponent(0) - input.getPreviousMousePosition().getComponent(0);
		double sign = 0;
		if(xDiff != 0.0)
			sign = xDiff / Math.abs(xDiff);
		//Get scalefactor as the square root of the difference
		double scaleFactor = Math.sqrt(Math.abs(xDiff)) * sign;

		steeringVector.scalarMultiply(scaleFactor * turnSpeed);

		//Add steeringVector to the forward
		attachedTo.setForward(Vec.normalize(Vec.add(attachedTo.getForward(), steeringVector)));

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
