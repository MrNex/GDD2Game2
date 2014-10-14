package state.objectstates;

import java.awt.Graphics2D;

import objects.GameObject;
import triggers.Trigger;

public class CaptiveRatState extends ObjectState {

	public CaptiveRatState() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void enter() {
		attachedTo.setTriggerable(true);
		attachedTo.addTrigger(new Trigger(){
			@Override
			public void action(GameObject triggeredBy) {
				System.out.println("Triggered");
				attachedTo.setRunning(false);
				attachedTo.setVisible(false);
			}
		});

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

}
