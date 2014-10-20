package state.enginestates;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import engine.Engine;
import engine.manager.CameraManager;
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

	private boolean swap;
	public TestState(){
		super();
		swap = false;
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
		testObj.setImage(content.getImage("ratImage"));
		testObj.setVisible(true);
		//Behavior
		testObj.setState(new PlayerAimState());
		testObj.setRunning(true);
		//Components
		testObj.setTriggerable(true);
		//Add gameobject to current state
		addObj(testObj);
		
		//Set camera to follow player
		((CameraManager)Engine.currentInstance.getManager(Engine.Managers.CAMERAMANAGER)).setFollow(testObj.getPos());

		//Load test level
		//ContentManager content = (ContentManager)Engine.currentInstance.getManager(Engine.Managers.CONTENTMANAGER);
		content.getLevel("TestLevel").load();
		
		//The follwing code loads the old level
		/*
		firstSection();
		secondSection();
		thirdSection();
		*/
	}

	/*
	 * sets up the scene for the first section
	 */
	public void firstSection(){
		//Captive Rats
		GameObject captiveRat1 = new GameObject(400, 350, 20, 20, new Vec(1, 0));
		captiveRat1.setShape(new Ellipse2D.Double());
		captiveRat1.setVisible(true);
		captiveRat1.setColor(Color.orange);
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
		GameObject upper = borderWall(0,0,1200,20,new Vec(1,0));
		addObj(upper);

		//bottom boundary
		GameObject bottom = borderWall(0,685,1200,20,new Vec(1,0));
		addObj(bottom);

		//left boundary
		GameObject left = borderWall(0,20,20,665,new Vec(1,0));
		addObj(left);

		//right boundary
		GameObject right = borderWall(1180,20,20,300,new Vec(1,0));
		addObj(right);
		GameObject rightb = borderWall(1180,380,20,320,new Vec(1,0));
		addObj(rightb);



		GameObject midLine = wall(700,335,70,30,new Vec(1,0));
		addObj(midLine);

		GameObject buildingTop = wall(400, 200, 100,100, new Vec(1,0));
		addObj(buildingTop);

		GameObject buildingBot = wall(400, 400, 100,100, new Vec(1,0));;
		addObj(buildingBot);

		GameObject buildingLongMid = wall(550, 250, 50,200, new Vec(1,0));;
		addObj(buildingLongMid);

		GameObject longTop = wall(300, 20, 50,100, new Vec(1,0));
		addObj(longTop);

		GameObject longBot = wall(300, 585, 50,100, new Vec(1,0));
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
	
	public void secondSection(){
		//bottom
		GameObject bottom = borderWall(1200,685,665,20,new Vec(1,0));
		addObj(bottom);
		//right
		GameObject right = borderWall(1845,-200,20,900,new Vec(1,0));
		addObj(right);
		//top
		GameObject top = borderWall(1200,-150,665,20,new Vec(1,0));
		addObj(top);
		//bottom Left Wall
		GameObject wallBottomLeft = wall(1200,600,200,20,new Vec(1,0));
		addObj(wallBottomLeft);
		//bottom Right Wall 
		GameObject wallBottomRight = wall(1645,600,200,20,new Vec(1,0));
		addObj(wallBottomRight);
		
		//middle left top
		GameObject wallLeftTop = wall(1200,275,100,20,new Vec(1,0));
		addObj(wallLeftTop);
		//middle left top reset
		GameObject resetLeftTop = reseter(1200,286,100,10,new Vec(1,0),1110,335);
		addObj(resetLeftTop);
		//middle left
		GameObject wallMiddleLeft = wall(1300,275,20,200,new Vec(1,0));
		addObj(wallMiddleLeft);
		//middle right
		GameObject wallMiddleRight = wall(1720,275,20,200,new Vec(1,0));
		addObj(wallMiddleRight);
		
		
		//reset/kill object
		GameObject milkLeft = reseter(1200,684,100,10,new Vec(1,0),1110,335);
		addObj(milkLeft);
		//reset wall(bottom middle)
		GameObject milkMiddle = reseter(1400,684,245,10,new Vec(1,0),1110,335);
		addObj(milkMiddle);
		//reset wall(bottom right)
		GameObject milkRight = reseter(1745,684,100,10,new Vec(1,0),1110,335);
		addObj(milkRight);
		
		GameObject bouncey1 = bounceyWall(1719,275,10,200,new Vec(1,0));
		addObj(bouncey1);
		GameObject bouncey2 = bounceyWall(1311,275,10,200,new Vec(1,0));
		addObj(bouncey2);
		
		
		//basket
		GameObject capLeft = wall(1400,-10,20,10,new Vec(1,0));
		addObj(capLeft);
		GameObject capRight = wall(1622,-10,20,10,new Vec(1,0));
		addObj(capRight);
		
		GameObject leftBounce = bounceyWall(1400,0,10,250,new Vec(1,0));
		addObj(leftBounce);
		GameObject leftReseter = reseter(1410,0,10,250,new Vec(1,0),1110,335);
		addObj(leftReseter);
		
		GameObject rightBounce = bounceyWall(1632,0,10,250,new Vec(1,0));
		addObj(rightBounce);
		GameObject rightReseter = reseter(1622,0,10,250,new Vec(1,0),1110,335);
		addObj(rightReseter);
		
		GameObject bottomBounce = bounceyWall(1410,240,222,10,new Vec(1,0));
		addObj(bottomBounce);
		GameObject bottomWall = wall(1410,230,222,10,new Vec(1,0));
		addObj(bottomWall);
			
		
		//captive rat
		GameObject captiveRat1 = new GameObject(1220, 640, 20, 20, new Vec(1, 0));
		captiveRat1.setShape(new Ellipse2D.Double());
		captiveRat1.setVisible(true);
		captiveRat1.setColor(Color.orange);
		captiveRat1.setState(new CaptiveRatState());
		addObj(captiveRat1);
		
		GameObject captiveRat2 = new GameObject(1780, 640, 20, 20, new Vec(1, 0));
		captiveRat2.setShape(new Ellipse2D.Double());
		captiveRat2.setVisible(true);
		captiveRat2.setColor(Color.orange);
		captiveRat2.setState(new CaptiveRatState());
		addObj(captiveRat2);
		
		GameObject captiveRat3 = new GameObject(1510, 200, 20, 20, new Vec(1, 0));
		captiveRat3.setShape(new Ellipse2D.Double());
		captiveRat3.setVisible(true);
		captiveRat3.setColor(Color.orange);
		captiveRat3.setState(new CaptiveRatState());
		addObj(captiveRat3);
	}
	
	public void thirdSection(){
		
		//left border wall
		GameObject left = borderWall(280,-150,20,150,new Vec(1,0));
		addObj(left);
		
		//top border wall
		GameObject top = borderWall(300,-150,900,10,new Vec(1,0));
		addObj(top);
		
		//top bouncey object
		GameObject bouncey1 = bounceyWall(300,-140,900,10,new Vec(1,0));
		addObj(bouncey1);
		
		//bottom bouncey left
		GameObject bouncey2 = bounceyWall(300,-1,350,10,new Vec(1,0));
		addObj(bouncey2);
		//bottom bouncey right
		GameObject bouncey3 = bounceyWall(850,-1,350,10,new Vec(1,0));
		addObj(bouncey3);
		
		GameObject reseter = reseter(750,-100,10,70,new Vec(1,0),1200,-75);
		addObj(reseter);
		
		//captive rat
		GameObject captiveRat1 = new GameObject(340, -75, 20, 20, new Vec(1, 0));
		captiveRat1.setShape(new Ellipse2D.Double());
		captiveRat1.setVisible(true);
		captiveRat1.setColor(Color.orange);
		captiveRat1.setState(new CaptiveRatState());
		addObj(captiveRat1);
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
	public GameObject borderWall(int x, int y, int w, int h, Vec v){
		GameObject obj = new GameObject(x,y,w,h,v);
		obj.setShape(new Rectangle2D.Double());
		obj.setVisible(true);
		obj.setColor(Color.black);
		return obj;
	}
	
	public GameObject wall(int x, int y, int w, int h, Vec v){
		GameObject obj = new GameObject(x,y,w,h,v);
		obj.setShape(new Rectangle2D.Double());
		obj.setVisible(true);
		obj.setColor(Color.gray);
		return obj;
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
	
	public GameObject reseter(int x, int y, int w, int h, Vec v, final int px, final int py){
		GameObject obj = new GameObject(x,y,w,h,v);
		obj.setShape(new Rectangle2D.Double());
		obj.setVisible(true);
		obj.setColor(Color.white);
		obj.setTriggerable(true);
		obj.addTrigger(new Trigger(){

			@Override
			//triggered event
			public void action(GameObject triggeredBy, CollisionBuffer cBuff) {
				triggeredBy.setPos(new Vec(px,py));
				triggeredBy.setState(new PlayerAimState());
				((CameraManager)Engine.currentInstance.getManager(Engine.Managers.CAMERAMANAGER)).setFollow(triggeredBy.getPos());
			}
		});
		return obj;
	}
	
	public GameObject objectSwapper(int x, int y, int w, int h, Vec v){
		GameObject obj = new GameObject(x,y,w,h,v);
		obj.setShape(new Rectangle2D.Double());
		obj.setVisible(true);
		obj.setColor(Color.green);
		obj.setTriggerable(true);
		obj.addTrigger(new Trigger(){

			@Override
			//triggered event
			public void action(GameObject triggeredBy, CollisionBuffer cBuff) {
				swap = !swap;
			}
		});
		return obj;
	}
}
