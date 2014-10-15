package state.objectstates;

import java.awt.Graphics2D;

import engine.Engine;
import objects.GameObject;
import state.enginestates.EngineState;
import triggers.Trigger;

/**
 * An abstract class that extends ObjectState to separate collectable objects from the other objects
 * and determine what they do when they are collected
 * @author nygiants656
 *
 */
public abstract class CollectableState extends ObjectState {
	
	protected EngineState es = Engine.currentInstance.getCurrentState();
	
	public CollectableState() {
		
	}
	
	/**
	 * Adds the trigger that collects the object once the player touches it
	 */
	@Override
	public void enter() {
		attachedTo.setTriggerable(true);
		attachedTo.addTrigger(new Trigger(){
			@Override
			public void action(GameObject triggeredBy) {
				if (triggeredBy.getState() instanceof PlayerAimState) {
					collect(triggeredBy);
				}
			}
		});
	}

	@Override
	abstract public void update();

	@Override
	abstract public void drawEffects(Graphics2D g2d);

	@Override
	abstract public void exit();
	
	/**
	 * Determines what to do when the player collects the object
	 */
	public abstract void collect(GameObject triggeredBy);

}
