package state.objectstates;

import java.awt.Graphics2D;

import objects.GameObject;

/**
 * A captive rat's state in which it disappears when the player touches it and adds to the player's score
 * @author nygiants656
 *
 */
public class CaptiveRatState extends CollectableState {

	public CaptiveRatState() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
	}

	@Override
	public void drawEffects(Graphics2D g2d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit() {
	}
	
	/**
	 * Removes the captive rat and adds to the player's score
	 */
	@Override
	public void collect(GameObject triggeredBy) {
		es.removeObj(attachedTo);
	}

}
