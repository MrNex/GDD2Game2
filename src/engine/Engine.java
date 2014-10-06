package engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import engine.manager.InputManager;
import engine.manager.Manager;
import engine.manager.ScreenManager;
import state.*;
import state.enginestates.EngineState;
import state.enginestates.TestState;


/**
 * Engine which runs on finite state machine.
 * Uses EngineStates
 * @author Nex
 *
 */
public class Engine {

	//Static variables
	public static Engine currentInstance;

	//Attributes
	private boolean running;
	private EngineState currentState;
	private Manager[] managers;
	private Timer drawTimer;
	
	/**
	 * Constructs an engine
	 */
	public Engine() {
		init();
	}

	/**
	 * Initializes engine members
	 */
	public void init()
	{
		//TODO: Set the currentState
		currentState = new TestState();


		//Set internal variables
		running = false;

		
		//Create managers
		managers = new Manager[1];
		
		managers[0] = new InputManager();
		managers[1] = new ScreenManager();
		
		//Create timer for screen manager
		drawTimer = new Timer(1000/60, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Update screen
				managers[0].update();
			}
			
		});
		
		drawTimer.setRepeats(true);
		
		
	}

	/**
	 * Begins running the engine
	 */
	public void start()
	{
		//Set running to true
		running = true;
		//Run
		run();
	}

	/**
	 * This is the run loop for the engine.
	 * Constantly updates the current state of the engine.
	 */
	private void run()
	{
		drawTimer.start();
		while(running)
		{
			currentState.update();
		}
	}

	/**
	 * Gets the current state of the engine
	 * @return The current state of the engine
	 */
	public EngineState getCurrentState()
	{
		return currentState;
	}
	
	/**
	 * Gets an engine components manager.
	 * Index values are as follows:
	 * 0 - Input Manager
	 * 1 - Screen Manager
	 * @param index Index of the component manager needed
	 * @return The desired component manager
	 */
	public Manager getManager(int index){
		return managers[index];
	}

}
