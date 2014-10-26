package state.objectstates;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import mathematics.Vec;
import objects.GameObject;

/**
 * Defines a state which seeks a point on negative forward
 * vector of the seeking gameObject at it's center position
 * @author Nex
 *
 */
public class TailState extends ObjectState {

	//Static attributes
	static private double maxSpeed = 0.012;
	static private double maxForce = 0.006;
	static private double arrivalRadius = 2;
	static private double teleportRadius = 50;
	static public Ellipse2D ellipse = new Ellipse2D.Double(0.0, 0.0, 0.0, 0.0); 
	
	//Attributes
	private GameObject target;
	private Vec velocity;	
	
	/**
	 * Constructs a tailstate
	 * 
	 * @param toTail the gamebject you want this state to follow
	 */
	public TailState(GameObject toTail) {
		super();
		
		target = toTail;
		velocity = new Vec(2);
	}

	/**
	 * Called when an object enters this state.
	 * No preparations must be made here
	 */
	@Override
	public void enter() {
		velocity.setComponent(0, 0);
		velocity.setComponent(1, 0);
	}

	@Override
	public void update() {
		//Get the seek point
		Vec toSeek = getSeekPoint();
		
		double distanceToSeekPoint = Vec.subtract(toSeek, attachedTo.getCenter()).getMag();
		
		if(distanceToSeekPoint > TailState.teleportRadius){
			attachedTo.getPos().copy(target.getPos());
		}
		//If the seek point is further than the arrival distance
		else if(distanceToSeekPoint > TailState.arrivalRadius){
			//Get translation vector needed to seek to the seekpoint
			Vec translationVector = seek(toSeek);
			
			//Get new forward vector
			if(translationVector.getMag() > 0.0)
				attachedTo.setForward(Vec.normalize(translationVector));
			
			attachedTo.getPos().add(translationVector);
		}
	}
	
	/**
	 * Gets the point which the attached object should seek from the target
	 * Method obfuscation due to optimization
	 * @return A vector which the attached object should seek
	 */
	private Vec getSeekPoint(){
		return Vec.subtract(target.getCenter(), Vec.scalarMultiply(target.getForward(), target.getHeight() / 2.0));
	}
	
	/**
	 * Seeks a target point and returns a tralsnation
	 * vector needed to appear to "seek" the target
	 * @param seekPoint The point which this state wants to seek
	 * @return The transltion vector needed to make the attached object seek the target vector
	 */
	private Vec seek(Vec seekPoint){
		Vec forceVec = Vec.subtract(seekPoint, attachedTo.getCenter());
		
		forceVec.limit(TailState.maxSpeed);
		velocity.add(forceVec);
		
		velocity.limit(TailState.maxSpeed);
		return velocity;
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
