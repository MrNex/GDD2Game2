package triggers;

import objects.GameObject;
import objects.MovableGameObject;
import buffer.CollisionBuffer;

public class CheckpointTrigger extends Trigger {

	/**
	 * Constructs a checkPoint trigger
	 */
	public CheckpointTrigger() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Action taken when a checkPoint trigger is triggered
	 * Assert that the gameObject triggeredBy is a movableGameObject
	 * If so, set the attached gameObject as the activeCheckpoint of triggeredBy
	 */
	@Override
	public void action(GameObject triggeredBy, CollisionBuffer cBuff) {
		//Cast to movable game object
		MovableGameObject mObj = (MovableGameObject)triggeredBy;
		
		//Check for successful cast
		if(mObj != null){
			//set checkpoint
			mObj.setActiveCheckpoint(attachedTo);
		}
	}
}
