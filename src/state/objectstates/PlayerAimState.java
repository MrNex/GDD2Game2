package state.objectstates;

import java.awt.Color;
import java.awt.Graphics2D;

import engine.Engine;
import engine.Engine.Managers;
import engine.manager.InputManager;
import mathematics.Vec;

/**
 * The player runs on a finite state machine between two states
 * PlayerAimState and {@link PlayerFlyState}
 * 
 * PlayerAimState is when the player is grounded on a surface and aiming his/her next shot.
 * Upon pressing the left mouse button the players state will change to PlayerFlyState.
 * @author Nex
 *
 */
public class PlayerAimState extends ObjectState {

	/**
	 * Constructs playerAimSTate
	 */
	public PlayerAimState() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void enter() {
		// TODO Auto-generated method stub

	}

	/**
	 * Updates the gameObjects normal to point at the mouse.
	 * If the left mouse buton is pressed, swaps to PlayerFlyState
	 */
	@Override
	public void update() {
		//Get the inputmanager
		InputManager input =  (InputManager)Engine.currentInstance.getManager(Managers.INPUTMANAGER);
		
		//Create a forward vector pointing at the mouse
		Vec forward = new Vec(2);
		forward.copy(input.getMousePosition());
		forward.subtract(attachedTo.getCenter());
		
		
		//normalize it
		forward.normalize();
		attachedTo.setForward(forward);
		
		//Check mouse button
		if(input.isMouseButtonPressed(1)){
			attachedTo.setState(new PlayerFlyState());
		}

	}

	/**
	 * Draw a line segment representing the objects normal
	 */
	@Override
	public void drawEffects(Graphics2D g2d) {
		g2d.setColor(Color.cyan);
		//Vec lineStart = attachedTo.getCenter();
		Vec lineStart = new Vec(2);
		Vec lineEnd = new Vec(2);
		//lineEnd.copy(lineStart);
		//lineEnd.add(Vec.setMag(attachedTo.getForward(), 50));
		lineEnd.setComponent(0, 1);
		lineEnd.setMag(50);
		g2d.drawLine((int)lineStart.getComponent(0), (int)lineStart.getComponent(1), (int)lineEnd.getComponent(0), (int)lineEnd.getComponent(1));
	
		g2d.setColor(Color.red);
		Vec rStart = new Vec(2);
		Vec rEnd = new Vec(2);
		rEnd.setComponent(0, 1);
		rEnd.setMag(50);
		g2d.drawLine((int)rStart.getComponent(0), (int)rStart.getComponent(1), (int)rEnd.getComponent(0), (int)rEnd.getComponent(1));
		
		g2d.setColor(Color.red);
		Vec lStart = new Vec(2);
		Vec lEnd = new Vec(2);
		lEnd.setComponent(0, 1);
		lEnd.setMag(50);
		g2d.drawLine((int)lStart.getComponent(0), (int)lStart.getComponent(1), (int)lEnd.getComponent(0), (int)lEnd.getComponent(1));
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

}
