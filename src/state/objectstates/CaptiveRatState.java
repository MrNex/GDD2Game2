package state.objectstates;

import java.awt.Graphics2D;

import engine.Engine;
import objects.GameObject;
import state.enginestates.EngineState;
import triggers.Trigger;

public class CaptiveRatState extends ObjectState {
	
	EngineState es = Engine.currentInstance.getCurrentState();

	public CaptiveRatState() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void enter() {
		attachedTo.setTriggerable(true);
		attachedTo.addTrigger(new Trigger(){
			@Override
			public void action(GameObject triggeredBy) {
				es.removeObj(attachedTo);
				
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
