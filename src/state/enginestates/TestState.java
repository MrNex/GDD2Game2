package state.enginestates;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

import objects.GameObject;

public class TestState extends EngineState {

	public TestState(){
		super();
	}
	
	@Override
	public void init(){
		super.init();
		
		//Create a gameobject
		GameObject testObj = new GameObject(300, 200, 100, 300);
		testObj.setShape(new Ellipse2D.Double(testObj.getXPos(), testObj.getYPos(), testObj.getWidth(), testObj.getHeight()));
		testObj.setColor(Color.black);
		testObj.setVisible(true);
		
		//Add gameobject to current state
		addObj(testObj);
	}
	
	
}
