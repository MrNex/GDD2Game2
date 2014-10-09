package state.objectstates;

import java.awt.Graphics2D;

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

	@Override
	public void drawEffects(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

}
