package state.enginestates;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import engine.Engine;
import engine.manager.ContentManager;
import mathematics.Vec;
import objects.GameObject;
import objects.MovableGameObject;
import state.objectstates.CaptiveRatState;

import state.objectstates.PlayerAimState;
import state.objectstates.PlayerFlyState;

import triggers.Trigger;
import buffer.CollisionBuffer;

public class TestState extends EngineState {

	public TestState(){
		super();
	}

	@Override
	public void init(){
		super.init();
	}

	/**
	 * Creates the test state's test level!
	 */
	public void createTestLevel(){
		//Get reference to content manager
		ContentManager content = (ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER);

		//Create player
		//Create a gameobject
		GameObject testObj = new MovableGameObject(30, 340, 20, 20, new Vec(1, 0));
		//Visuals
		testObj.setShape(new Ellipse2D.Double());
		testObj.setColor(Color.orange);
		testObj.setImage(content.getImage("TestImg"));
		testObj.setVisible(true);
		//Behavior
		testObj.setState(new PlayerAimState());
		testObj.setRunning(true);
		//Components
		testObj.setTriggerable(true);
		//Add gameobject to current state
		addObj(testObj);


		//Captive Rats
		GameObject captiveRat1 = new GameObject(400, 350, 20, 20, new Vec(1, 0));
		captiveRat1.setShape(new Ellipse2D.Double());
		captiveRat1.setVisible(true);
		captiveRat1.setColor(Color.green);
		captiveRat1.setState(new CaptiveRatState());
		addObj(captiveRat1);


		//Create bouncy walls
		//Bounce test obj
		GameObject bounceObj1 = bounceyWall(800,100,35,100,new Vec(1,0));
		addObj(bounceObj1);
		GameObject bounceObj2 = bounceyWall(800, 300, 35, 100, new Vec(1,0));
		addObj(bounceObj2);
		GameObject bounceObj3 = bounceyWall(800, 500, 35, 100, new Vec(1,0));
		addObj(bounceObj3);

		//Walls to bound the screen
		//Top boundary
		GameObject upper = new GameObject(0,0,1200,20,new Vec(1,0));
		upper.setShape(new Rectangle2D.Double());
		upper.setVisible(true);
		//upper.setColor(Color.pink);
		upper.setColor(Color.black);
		addObj(upper);

		//bottom boundary
		GameObject bottom = new GameObject(0,685,1200,20,new Vec(1,0));
		bottom.setShape(new Rectangle2D.Double());
		bottom.setVisible(true);
		//bottom.setColor(Color.pink);
		bottom.setColor(Color.black);
		addObj(bottom);

		//left boundary
		GameObject left = new GameObject(0,20,20,665,new Vec(1,0));
		left.setShape(new Rectangle2D.Double());
		left.setVisible(true);
		//left.setColor(Color.red);
		left.setColor(Color.black);
		addObj(left);

		//right boundary
		GameObject right = new GameObject(1180,20,20,665,new Vec(1,0));
		right.setShape(new Rectangle2D.Double());
		right.setVisible(true);
		//right.setColor(Color.red);
		right.setColor(Color.black);
		addObj(right);



		GameObject midLine = new GameObject(700,335,70,30,new Vec(1,0));
		midLine.setShape(new Rectangle2D.Double());
		midLine.setVisible(true);
		midLine.setColor(Color.black);
		addObj(midLine);

		GameObject buildingTop = new GameObject(400, 200, 100,100, new Vec(1,0));
		buildingTop.setShape(new Rectangle2D.Double());
		buildingTop.setVisible(true);
		buildingTop.setColor(Color.gray);
		addObj(buildingTop);

		GameObject buildingBot = new GameObject(400, 400, 100,100, new Vec(1,0));
		buildingBot.setShape(new Rectangle2D.Double());
		buildingBot.setVisible(true);
		buildingBot.setColor(Color.gray);
		addObj(buildingBot);

		GameObject buildingLongMid = new GameObject(550, 250, 50,200, new Vec(1,0));
		buildingLongMid.setShape(new Rectangle2D.Double());
		buildingLongMid.setVisible(true);
		buildingLongMid.setColor(Color.gray);
		addObj(buildingLongMid);

		GameObject longTop = new GameObject(300, 20, 50,100, new Vec(1,0));
		longTop.setShape(new Rectangle2D.Double());
		longTop.setVisible(true);
		longTop.setColor(Color.gray);
		addObj(longTop);

		GameObject longBot = new GameObject(300, 585, 50,100, new Vec(1,0));
		longBot.setShape(new Rectangle2D.Double());
		longBot.setVisible(true);
		longBot.setColor(Color.gray);
		addObj(longBot);

		//hallway
		GameObject hallTop = new GameObject(1000, 275, 200,50, new Vec(1,0));
		hallTop.setShape(new Rectangle2D.Double());
		hallTop.setVisible(true);
		hallTop.setColor(Color.darkGray);
		addObj(hallTop);

		GameObject hallBot = new GameObject(1000, 375, 200,50, new Vec(1,0));
		hallBot.setShape(new Rectangle2D.Double());
		hallBot.setVisible(true);
		hallBot.setColor(Color.darkGray);
		addObj(hallBot);

	}

	/**
	 * 
	 * @param x position in world
	 * @param y position in world
	 * @param w width of rect to make
	 * @param h height of rect to make
	 * @param v vector for rotation
	 * @return returns the object made
	 */
	public GameObject bounceyWall(int x, int y, int w, int h, Vec v){
		//GameObject obj = new GameObject(800,200,35,100,new Vec(1,0));
		GameObject obj = new GameObject(x,y,w,h,v);
		obj.setShape(new Rectangle2D.Double());
		obj.setVisible(true);
		obj.setColor(Color.red);
		obj.setTriggerable(true);
		obj.addTrigger(new Trigger(){

			@Override
			//triggered event
			public void action(GameObject triggeredBy, CollisionBuffer cBuff) {
				// TODO Auto-generated method stub
				triggeredBy.setState(new PlayerFlyState());
				
				Vec force;
				Vec reflect;

				//Algorithm outline for fixing "bounce direction" of bouncing platforms
				//Determine if triggeredBy is cBuff's obj1 or obj2
				if(triggeredBy == cBuff.obj1){
					//Get the reflect vector, the collided side of the gameObject attached to the trigger rotated by 90 degrees CCW
					reflect = Vec.rotate(cBuff.obj2CollidedSide, Math.PI/2.0);
				}
				else{
					reflect = Vec.rotate(cBuff.obj1CollidedSide, Math.PI/2.0);
				}

				//Reflect triggeredBy's reverse forward vector over reflect
				//Reverse forward vector
				force = triggeredBy.getForward();
				force.setMag(-1);
				
				//Make sure both are normalized
				reflect.normalize();
				force.normalize();
				
				//Get angle between
				double aBetween = Math.atan2(reflect.getComponent(1), reflect.getComponent(0)) - Math.atan2(force.getComponent(1), force.getComponent(0));
			
				//Multiply by 2
				aBetween *= 2;
				
				//Rotate forward vector
				force.rotate(aBetween);
				
				force.normalize();
				
				//Set triggeredBy's forward to force
				triggeredBy.setForward(force);
			}
		});
		return obj;
	}
}
