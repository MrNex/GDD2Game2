package state.objectstates;

import java.awt.Graphics2D;

import objects.GameObject;
import state.objectstates.ObjectState;

public class CheckpointState extends ObjectState {

	//Static variables
	static GameObject activeCheckpoint = null;
	
	/**
	 * Constructs a checkpoint state
	 * 
	 * Checkpoints are not active by default.
	 * Only one checkPoint will be active at a time.
	 */
	public CheckpointState() {
		
	}

	/**
	 * Called when an object has acheckPoint state attached to it.
	 * Set attached object to triggerable and Add a checkPoint trigger
	 * Set attached object to not solid, so player does not collide with or get stuck inside of checkPoints.
	 */
	@Override
	public void enter() {
		//Trigger setup
		attachedTo.setTriggerable(true);
		//attachedTo.addTrigger();		//TODO: Add checkPointTrigger

	}

	/**
	 * Updates a checkPoint state.
	 * This shouldn't need to do anything.
	 */
	@Override
	public void update() {

	}

	/**
	 * Draws the effects of a checkPointState
	 * This also probably won't need to do anything by default.
	 */
	@Override
	public void drawEffects(Graphics2D g2d) {
		// TODO Auto-generated method stub

	}

	/**
	 * Upon exiting a checkpoint state, if the Active Checkpoint is the attached gameObject, set the active checkpoint to null.
	 */
	@Override
	public void exit() {
		if(CheckpointState.activeCheckpoint == attachedTo){
			CheckpointState.activeCheckpoint = null;
		}
	}

}
