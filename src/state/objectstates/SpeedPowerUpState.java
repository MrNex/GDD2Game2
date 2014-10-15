package state.objectstates;

import java.awt.Graphics2D;

import objects.GameObject;

public class SpeedPowerUpState extends CollectableState {

	public SpeedPowerUpState() {

	}

	@Override
	public void update() {
	}

	@Override
	public void drawEffects(Graphics2D g2d) {
	}

	@Override
	public void exit() {
	}
	
	/**
	 * Removes the power-up and increases the player's speed
	 */
	@Override
	public void collect(GameObject triggeredBy) {
		es.removeObj(attachedTo);
	}

}
