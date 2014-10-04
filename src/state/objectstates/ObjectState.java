package state.objectstates;

import objects.GameObject;

/**
 * Object state defines a state within {@link objects.GameObject}'s finite state machine
 * @author Nex
 *
 */
public abstract class ObjectState {

	protected GameObject attachedTo;

	public ObjectState() {

	}

	abstract public void enter();

	abstract public void update();

	abstract public void exit();

	public void setAttachedGameObject(GameObject attachTo){
		attachedTo = attachTo;
	}

}
