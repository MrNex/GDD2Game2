package engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

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
		
		managers[0] = new ScreenManager();
		
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

	private void run()
	{
		drawTimer.start();
		while(running)
		{
			currentState.update();
		}
	}

	public EngineState getCurrentState()
	{
		return currentState;
	}

}
