package triggers;

import mathematics.Vec;
import objects.GameObject;
import objects.MovableGameObject;
import state.objectstates.PlayerAimState;
import state.objectstates.PlayerFlyState;
import buffer.CollisionBuffer;

/**
 * Defines a trigger which on action will revert triggeredBy
 * to the center position of it's activeCheckpoint.
 * @author Nex
 *
 */
public class DeathTrigger extends Trigger {
	
	/**
	 * Constructs a deathTrigger
	 */
	public DeathTrigger() {
		
	}

	/**
	 * Revert triggeredBy back to the center position of it's last active checkpoint
	 * If triggeredBy is not a movable game object this method does nothing.
	 */
	@Override
	public void action(GameObject triggeredBy, CollisionBuffer cBuff) {
		MovableGameObject mObj = (MovableGameObject)triggeredBy;
		if(mObj != null){
			//Get the center position of the last active checkPoint of this movable game object
			Vec cpCenter = mObj.getActiveCheckpoint().getCenter();
			//Decrement cpCenter by triggeredBy's dimensions to allow exact center of trigered by to land on center of activeCheckpoint
			cpCenter.incrementComponent(0, triggeredBy.getWidth()/-2.0);
			cpCenter.incrementComponent(1, triggeredBy.getHeight()/-2.0);
			
			//Set position of triggeredBy to cpCenter
			triggeredBy.setPos(cpCenter);
			
			//Refresh triggeredBy so it's previousPosition gets set to it's currentPosition.
			//In case when the position is set to cpCenter & it is colliding with something it does not jump back and forth.
			mObj.refresh();
		}

	}

}
