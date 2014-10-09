package state.objectstates;

import java.awt.Graphics2D;

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
	
	//Accessors
	public void setAttachedGameObject(GameObject attachTo){
		attachedTo = attachTo;
	}

	/**
	 * Will be called on attaching a gameobject to this state.
	 */
	abstract public void enter();

	/**
	 * Updates this state. All state logic is done here.
	 */
	abstract public void update();
	
	/**
	 * Draws any debugging / added effects this state has.
	 * This also allows (and was originally intended) for visual debugging capabilities.
	 * @param g2d reference to renderer.
	 */
	abstract public void drawEffects(Graphics2D g2d);

	/**
	 * Will be called on an object changing from this state.
	 * Used for cleanup / changing back any altered properties of the attached gameObject
	 */
	abstract public void exit();

}
