package triggers;

import mathematics.Vec;
import objects.GameObject;
import state.objectstates.PlayerFlyState;
import buffer.CollisionBuffer;

/**
 * A trigger which bounces a colliding player off of it
 * @author Nex
 *
 */
public class BounceTrigger extends Trigger {

	/**
	 * Constructs a bounce trigger
	 */
	public BounceTrigger() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Reflects the colliding object's forward over a vector perpendicular to the vector of collision on the attached object
	 * And negates it
	 */
	@Override
	public void action(GameObject triggeredBy, CollisionBuffer cBuff) {
		// TODO Auto-generated method stub
		triggeredBy.setState(new PlayerFlyState());
		
		Vec force;
		Vec reflect;

		//Algorithm outline for fixing "bounce direction" of bouncing platforms
		//Determine if triggeredBy is cBuff's obj1 or obj2
		if(triggeredBy == cBuff.obj1){
			//Get the reflect vector, the collided side of the gameObject attached to the trigger rotated by 90 degrees CCW
			reflect = Vec.rotate(cBuff.obj2CollidedSide, Math.PI/2.0);
		}
		else{
			reflect = Vec.rotate(cBuff.obj1CollidedSide, Math.PI/2.0);
		}

		//Reflect triggeredBy's reverse forward vector over reflect
		//Reverse forward vector
		force = triggeredBy.getForward();
		force.setMag(-1);
		
		//Make sure both are normalized
		reflect.normalize();
		force.normalize();
		
		//Get angle between
		double aBetween = Math.atan2(reflect.getComponent(1), reflect.getComponent(0)) - Math.atan2(force.getComponent(1), force.getComponent(0));
	
		//Multiply by 2
		aBetween *= 2;
		
		//Rotate forward vector
		force.rotate(aBetween);
		
		force.normalize();
		
		//Set triggeredBy's forward to force
		triggeredBy.setForward(force);
		
	}

}
