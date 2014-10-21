package state.objectstates;

import java.awt.Color;
import java.awt.Graphics2D;

import buffer.CollisionBuffer;
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


	//Accessors
	public double getMovementSpeed(){
		return movementSpeed;
	}

	public PlayerFlyState() {
		movementSpeed = 0.002;
		turnSpeed = 0.006;
	}

	/**
	 * Helper function so I don't need to keep casting attachedTo
	 * @return
	 */
	private MovableGameObject getPlayer(){
		return (MovableGameObject)attachedTo;
	}

	/**
	 * Called when the player enters player fly state.
	 * Defines, initializes, and attaches trigger which causes toggling back to
	 * PlayerAimState on collision.
	 */
	@Override
	public void enter() {
		attachedTo.addTrigger(new Trigger(){

			/**
			 * Trigger which causes player to swap to PlayerAimState upon colliding
			 * With a solid gameObject. 
			 * This trigger gets removed upon being activated.
			 */
			@Override
			public void action(GameObject triggeredBy, CollisionBuffer cBuff) {
				if(triggeredBy.isSolid())
				{
					attachedTo.setState(new PlayerAimState());
					attachedTo.removeTrigger(this);
				}
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


		Vec mouseVector = new Vec(2);


		//Find the difference in the previous and current mouse position on the x axis
		double xDiff = input.getMousePosition().getComponent(0) - input.getPreviousMousePosition().getComponent(0);
		double yDiff = input.getMousePosition().getComponent(1) - input.getPreviousMousePosition().getComponent(1);
		mouseVector.setComponent(0, xDiff);
		mouseVector.setComponent(1, yDiff);

		//dot em
		//take number determine + or -
		//look at sign
		//once you have those, do exactly what Nick does with Scale factor instead of xdiff do result of dot product
		double dottedDifference = mouseVector.dot(steeringVector);
		double dotSign = 0;
		if(dottedDifference != 0.0)
		{
			dotSign = dottedDifference/Math.abs(dottedDifference);
		}

		double scaleFactor = Math.abs(dottedDifference) * dotSign;

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

	/**
	 * Draws the players forward, right, and -right vectors to outline the players coordinate system.
	 * Originally implemented for debugging but I find it is actually helpful to gamePlay.
	 */
	@Override
	public void drawEffects(Graphics2D g2d) {
		g2d.setColor(Color.cyan);
		Vec lineStart = new Vec(2);
		Vec lineEnd = new Vec(2);
		lineEnd.setComponent(0, 1);
		lineEnd.setMag(50);
		g2d.drawLine((int)lineStart.getComponent(0), (int)lineStart.getComponent(1), (int)lineEnd.getComponent(0), (int)lineEnd.getComponent(1));

		g2d.setColor(Color.red);
		Vec rStart = new Vec(2);
		Vec rEnd = new Vec(2);
		rEnd.setComponent(1, 1);
		rEnd.setMag(15);
		g2d.drawLine((int)rStart.getComponent(0), (int)rStart.getComponent(1), (int)rEnd.getComponent(0), (int)rEnd.getComponent(1));

		g2d.setColor(Color.red);
		Vec lStart = new Vec(2);
		Vec lEnd = new Vec(2);
		lEnd.setComponent(1, -1);
		lEnd.setMag(15);
		g2d.drawLine((int)lStart.getComponent(0), (int)lStart.getComponent(1), (int)lEnd.getComponent(0), (int)lEnd.getComponent(1));

	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

}
