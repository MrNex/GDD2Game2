package triggers;

import engine.Engine;
import objects.GameObject;
import state.enginestates.GameState;
import buffer.CollisionBuffer;

/**
 * This class defines a trigger which causes the next level to load.
 * @author Nex
 *
 */
public class NextLevelTrigger extends Trigger{
	
	/**
	 * Creates a next level trigger
	 */
	public NextLevelTrigger() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void action(GameObject triggeredBy, CollisionBuffer cBuff) {
		//Get reference to current gamestate
		GameState state = (GameState)Engine.currentInstance.getCurrentState();
		
		//If the player is the gameObject which triggered this trigger
		if(triggeredBy == state.getPlayer()){
			//Tell the gameState to load the next level
			state.loadNextLevel();
		}
	}

}
