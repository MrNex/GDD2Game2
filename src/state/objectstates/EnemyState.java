package state.objectstates;

import mathematics.Vec;

public class EnemyState extends ObjectState {
	
	private Vec movementVec;

	public EnemyState() {
	}

	@Override
	public void enter() {
		movementVec = new Vec();
		attachedTo.setRunning(true);
		attachedTo.setVisible(true);
		movementVec.setMag(12);
	}

	@Override
	public void update() {
		attachedTo.setPos(movementVec);
	}

	@Override
	public void exit() {
		attachedTo.setVisible(false);
		attachedTo.setRunning(false);

	}

}
