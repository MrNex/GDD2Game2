package engine.manager;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import engine.Engine;
import mathematics.Vec;

public class InputManager extends Manager implements KeyListener, MouseListener{

	//Attributes
	private boolean[] keys;
	private boolean[] mButtons;
	private Vec mousePosition;
	private Vec previousMousePosition;
	
	
	/**
	 * Constructs a new input manager
	 */
	public InputManager() {
		//Initialize input manager
		init();
	}

	/**
	 * Initializes all member variables here
	 */
	@Override
	public void init() {
		//Initialize array of keys
		keys = new boolean[256];
		
		//Initialize array of mouseButtons
		mButtons = new boolean[MouseInfo.getNumberOfButtons()];
	}

	
	/**
	 * Updates the mouse positions
	 */
	@Override
	public void update() {
		previousMousePosition = mousePosition;
		mousePosition = getMousePosition();
		
	}

	/**
	 * Not currently in use
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Not currently in use
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Not currently in use
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Registers a mouse button press and stores true under the correct index of mouse button presses
	 */
	@Override
	public void mousePressed(MouseEvent mousePress) {
		mButtons[mousePress.getButton()] = true;
		
	}

	/**
	 * Registers a mouse button release and stores false under the correct index of mouse button presses
	 */
	@Override
	public void mouseReleased(MouseEvent mouseRelease) {
		mButtons[mouseRelease.getButton()] = false;
		
	}

	/**
	 * Marks a key as being pressed in the array of key states (keys)
	 */
	@Override
	public void keyPressed(KeyEvent keyPress) {
		keys[keyPress.getKeyCode()] = true;
		
	}

	/**
	 * Marks a key as being released in the array of key states
	 */
	@Override
	public void keyReleased(KeyEvent keyPress) {
		keys[keyPress.getKeyCode()] = false;
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Gets the mouse position in window space
	 * @return A vector containing the mouse positionin window space
	 */
	private Vec getMousePosition(){
		//Get the mouse's screen position
		Point globalMousePos = MouseInfo.getPointerInfo().getLocation();
		//Retrieve reference to screen manager
		ScreenManager ref = (ScreenManager)Engine.currentInstance.getManager(1);
		//Retrieve windows position
		Point windowPos = ref.getWindow().getLocation();
		
		//Create a vector for the mouse position
		Vec relMousePos = new Vec(2);
		relMousePos.setComponent(0, globalMousePos.x - windowPos.x);
		relMousePos.setComponent(1, globalMousePos.y - windowPos.y);
		
		return relMousePos;
	}

}
