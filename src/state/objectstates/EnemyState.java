package state.objectstates;

import java.awt.Graphics2D;
import java.awt.Window;

import engine.Engine;
import engine.Engine.Managers;
import engine.manager.ScreenManager;
import mathematics.Vec;

/**
 * The enemy moves at a given angle and bounces off the sides of the window
 * @author nygiants656
 *
 */
public class EnemyState extends ObjectState {
	
	private Vec movementVec;
	private Window win;
	
	/**
	 * Constructs the EnemyState and links the window to EnemyState
	 */
	public EnemyState() {
		ScreenManager sm = (ScreenManager)Engine.currentInstance.getManager(Managers.SCREENMANAGER);
		win = sm.getWindow();
	}

	@Override
	public void enter() {
		movementVec = new Vec(.6, .3);
		movementVec.setMag(.002);
	}

	@Override
	public void update() {
		
		//Checks for the object hitting the boundaries of the window and reverses its direction
		
		//Check X position
		attachedTo.setPos(Vec.add(attachedTo.getPos(), movementVec));
		if (attachedTo.getXPos() + attachedTo.getWidth() > win.getX() + win.getWidth() || 
				attachedTo.getXPos() < win.getX()) {
			movementVec.setComponent(0, -movementVec.getComponent(0));
			movementVec.setMag(.002);
		}
		//Check Y Position
		if (attachedTo.getYPos() + attachedTo.getHeight() > win.getX() + win.getHeight() || 
				attachedTo.getYPos() < win.getY()) {
			movementVec.setComponent(1, -movementVec.getComponent(1));
			movementVec.setMag(.002);
		}
		
		attachedTo.updateShape();
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
