package state.objectstates;

import java.awt.Graphics2D;

import mathematics.Vec;
import state.enginestates.GameState;
import engine.Engine;

/**
 * A state which forces the player to it's position if the 
 * player has not touched a checkpoint yet
 * @author Nex
 *
 */
public class StartpointState extends ObjectState{

	/**
	 * Constructs a start point state
	 */
	public StartpointState() {

	}

	/**
	 * Called upon an object entering this state
	 * 
	 * No state entrance precautions must be made
	 */
	@Override
	public void enter() {
		
	}

	/**
	 * Updates this state, checks if the player has an active checkpoint yet
	 * If the player doesn't, the player is moved to this gameObject's position.
	 * If the player does, this state is removed from the object which contains it.
	 */
	@Override
	public void update() {
		//Get reference to gamestate
		GameState state = (GameState)Engine.currentInstance.getCurrentState();
		
		//Check if player does not have an active check point
		if(state.getPlayer().getActiveCheckpoint() == null){
			//Move player's position to this object's position
			state.getPlayer().getPos().copy(attachedTo.getPos());
			System.out.println(state.getPlayer().getPos().toString());
		}
		else{
			//Remove this state from it's object
			//attachedTo.setState(null);
		}
		
	}

	/**
	 * Draws the effects of this state
	 * 
	 * There are no effects being drawn in this state
	 */
	@Override
	public void drawEffects(Graphics2D g2d) {
		
	}

	/**
	 * Called upon an object exiting this state
	 */
	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

}
