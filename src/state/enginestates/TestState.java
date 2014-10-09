package state.enginestates;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import mathematics.Vec;
import objects.GameObject;
import objects.MovableGameObject;
import state.objectstates.EnemyState;
import state.objectstates.PlayerAimState;
import state.objectstates.PlayerState;

public class TestState extends EngineState {

	public TestState(){
		super();
	}

	@Override
	public void init(){
		super.init();

		//Create a gameobject
		GameObject testObj = new MovableGameObject(520, 480, 20, 20, new Vec(0, -1));

		//Visuals
		testObj.setShape(new Ellipse2D.Double());
		testObj.setColor(Color.orange);
		testObj.setVisible(true);

		//Behavior
		testObj.setState(new PlayerAimState());
		testObj.setRunning(true);

		//Components
		testObj.setTriggerable(true);

		//Add gameobject to current state
		addObj(testObj);


		//Make another!
		GameObject testObj2 = new GameObject(500, 500, 50, 50, new Vec(1, 0));

		//Visuals
		testObj2.setShape(new Rectangle2D.Double());
		testObj2.setVisible(true);

		addObj(testObj2);

		//And another!
		GameObject testObj3 = new GameObject(100, 100, 50, 50, new Vec(1, 0));

		//Visuals
		testObj3.setShape(new Rectangle2D.Double());
		testObj3.setVisible(true);

		addObj(testObj3);
		
		
	//Uncomment to see enemy behavior
//		//Create an enemy
//		GameObject testObj4 = new MovableGameObject(300, 300, 50, 50, new Vec(0, 0));
//
//		//Visuals
//		testObj.setShape(new Ellipse2D.Double());
//		testObj.setColor(Color.red);
//		testObj.setVisible(true);
//
//		//Behavior
//		testObj.setState(new EnemyState());
//		testObj.setRunning(true);
//		
//		addObj(testObj4);
	}



}
