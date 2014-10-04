package engine;

import state.*;


/**
 * Engine which runs on finite state machine.
 * Uses EngineStates
 * @author Nex
 *
 */
public class Engine {


	//Attributes
	private boolean running;
	private State currentState;

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
		//currentState = new ;


		//Set internal variables
		running = false;

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
		while(running)
		{
			currentState.update();
		}
	}

	public State getCurrentState()
	{
		return currentState;
	}

}
