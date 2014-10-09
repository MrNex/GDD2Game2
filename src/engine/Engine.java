package engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import engine.manager.CollisionManager;
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

	//Enums
	public enum Managers{
		INPUTMANAGER, COLLISIONMANAGER, SCREENMANAGER
	}
	
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
		//Set this as current instance of engine
		currentInstance = this;
		
		//Creation of currentState moved from here due to null pointer exception

		//Set internal variables
		running = false;

		
		//Create managers
		managers = new Manager[3];
		
		managers[Managers.INPUTMANAGER.ordinal()] = new InputManager();
		managers[Managers.COLLISIONMANAGER.ordinal()] = new CollisionManager();
		managers[Managers.SCREENMANAGER.ordinal()] = new ScreenManager();
		
		//Create currentState ****Moved here to fix null pointer exception****
		currentState = new TestState();
		
		//Create timer for screen manager
		drawTimer = new Timer(1000/60, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Update screen
				managers[Engine.Managers.SCREENMANAGER.ordinal()].update();
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
		//Begin drawloop
		drawTimer.start();
		//Run
		run();
	}

	/**
	 * This is the run loop for the engine.
	 * Constantly updates the current state of the engine.
	 */
	private void run()
	{
		while(running)
		{
			//Update managers
			managers[Managers.INPUTMANAGER.ordinal()].update();
			
			//TODO: Offload to statemanager to keep track of stateStack
			currentState.update();
			
			//After objects update, update collisions.
			managers[Managers.COLLISIONMANAGER.ordinal()].update();
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
	public Manager getManager(Managers manager){
		return managers[manager.ordinal()];
	}

}
