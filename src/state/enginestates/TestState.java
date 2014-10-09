package state.enginestates;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import mathematics.Vec;
import objects.GameObject;
import objects.MovableGameObject;
import state.objectstates.PlayerState;

public class TestState extends EngineState {

	public TestState(){
		super();
	}
	
	@Override
	public void init(){
		super.init();
		
		//Create a gameobject
		GameObject testObj = new MovableGameObject(520, 480, 20, 20, new Vec(3));
		
		//Visuals
		testObj.setShape(new Ellipse2D.Double());
		testObj.setColor(Color.orange);
		testObj.setVisible(true);
		
		//Behavior
		testObj.setState(new PlayerState());
		testObj.setRunning(true);
		
		//Add gameobject to current state
		addObj(testObj);
		
		
		//Make another!
		GameObject testObj2 = new GameObject(500, 500, 50, 50, new Vec(3));
		
		//Visuals
		testObj2.setShape(new Rectangle2D.Double());
		testObj2.setVisible(true);
	
		addObj(testObj2);
	}
	
	
	
}
