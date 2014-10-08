package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RectangularShape;
import java.awt.image.BufferedImage;

import mathematics.*;
import state.objectstates.ObjectState;

public class GameObject {

	//Attributes
	protected Vec position;
	protected Vec forward, right;
	protected double width, height;
	protected boolean visible, running;
	protected RectangularShape shape;
	protected BufferedImage image;
	protected Color color;
	protected ObjectState currentState;

	/**
	 * Creates a basic GameObject with a position and size
	 * GameObject defaults to not running and not visible with a null state
	 * @param xx X Position in worldspace
	 * @param yy Y Position in worlspace
	 * @param w object width
	 * @param h object height
	 * @param fwd Forward vector, which direction is this gameobject facing.
	 */
	public GameObject(double xx, double yy, double w, double h, Vec fwd) {
		//Set designated attributes
		position = new Vec(xx, yy);
		
		//Set directional vectors
		forward = fwd;
		right = Vec.rotate(forward, -Math.PI/2);
		

		width = w;
		height = h;

		//Set default attributes
		visible = false;
		running = false;

		currentState = null;

		shape = null;
		color = Color.black;

		image = null;
		
		
	}

	//Accessors
	/**
	 * Gets the position vector
	 * @return the position vector
	 */
	public Vec getPos(){
		return position;
	}

	/**
	 * Sets the position vector
	 * @param v New position vector
	 */
	public void setPos(Vec v){
		position = v;
	}

	/**
	 * Gets the X component of the position vector
	 * @return
	 */
	public double getXPos(){
		return position.getComponent(0);
	}

	/**
	 * Gets the Y component of the position {@link mathematics.Vec}
	 * @return the Y component of position {@link mathematics.Vec}
	 */
	public double getYPos(){
		return position.getComponent(1);
	}
	
	/**
	 * Gets the width of the gameobject's bounding box
	 * @return a double containing the width of the bounding box
	 */
	public double getWidth(){
		return width;
	}
	
	/**
	 * Gets the height of the gameobjects bounding box
	 * @return the height of the bounding box
	 */
	public double getHeight(){
		return height;
	}

	/**
	 * Gets the forward vector of the gameobject
	 * @return A vector representing the direction which this gameObject s facing
	 */
	public Vec getForward(){
		return forward;
	}
	
	/**
	 * Sets the forward vector of the gameObject
	 * @param v Vector representing a new forward vector.
	 */
	public void setForward(Vec v){
		forward = v;
	}
	
	/**
	 * Sets the state of an {@link GameObject}.
	 * 
	 * If object isn't working see {@link setRunning()}
	 * @param newState State to attach to object
	 */
	public void setState(ObjectState newState){
		//If not leaving a null state
		if(currentState != null)
			currentState.exit();
		currentState = newState;

		//If not going into a null state
		if(newState != null){
			newState.setAttachedGameObject(this);
			newState.enter();
		}
	}

	/**
	 * Gets the visibility of this gameobject
	 * @return Whether or not the object is visible
	 */
	public boolean isVisible(){
		return visible;
	}
	
	/**
	 * Sets the visibility of this gameObject
	 * @param isVisible is the object visible
	 */
	public void setVisible(boolean isVisible){
		visible = isVisible;
	}

	/**
	 * Gets whethe or not this gameobject is running.
	 * @return Whether or not the gameobject is running.
	 */
	public boolean isRunning(){
		return running;
	}
	
	/**
	 * Sets whether or not the gameObject is running
	 * @param isRunning Is the gameObject running
	 */
	public void setRunning(boolean isRunning){
		running = isRunning;
	}

	/**
	 * Sets the shape of the gameObject
	 * @param newShape The new shape
	 */
	public void setShape(RectangularShape newShape){
		//Set the shape
		shape = newShape;
		//if its not null, update it to my position
		if(shape != null){
			updateShape();
		}
	}

	/**
	 * Sets the image of the gameObject
	 * @param newImage The new image
	 */
	public void setImage(BufferedImage newImage){
		image = newImage;
	}

	/**
	 * Sets the color of the GameObject
	 * @param newColor The new color
	 */
	public void setColor(Color newColor){
		color = newColor;
	}

	/**
	 * Sets the shape and color of the gameobject
	 * @param newShape The new shape
	 * @param color The new color
	 */
	public void setShape(RectangularShape newShape, Color color)
	{
		setShape(newShape);
		setColor(color);
	}
	

	/**
	 * Updates the current state of the gameObject if this object is running
	 */
	public void update(){
		if(running){
			currentState.update();
		}
	}

	/**
	 * Draws the image of this gameObject if the object is visible
	 * IF there is no image, but the object is visible, this will 
	 * draw the shape of the gameobject.
	 * @param g2d Graphics object to draw with
	 */
	public void draw(Graphics2D g2d){
		if(visible)
		{
			//If they have an image
			if(image != null){
				g2d.drawImage(image,
						(int)position.getComponent(0), (int)position.getComponent(1), 
						(int)(position.getComponent(0) + width), (int)(position.getComponent(1) + height), 
						0, 0, image.getWidth(), image.getHeight(), null);
			}
			else{
				//Set the color
				g2d.setColor(color);
				//Fill the shape
				g2d.fill(shape);
			}
		}
	}

	/**
	 * Updates the shape to match the size and position of this gameObject
	 */
	public void updateShape(){
		if(shape != null)
			shape.setFrame(position.getComponent(0), position.getComponent(1), width, height);
	}

	
	/**
	 * Checks if the bounding box of this obj is intersecting the bounding box of another obj
	 * @param obj GameObject to check with
	 * @return Whether this gameobject is intersecting with obj
	 */
	public boolean isColliding(GameObject obj){

		//If the left side of this is to the left  right side of obj and the right side of this is to the right of the left side of obj
		if(position.getComponent(0) < obj.position.getComponent(0) + this.width && this.position.getComponent(0) + this.width > obj.position.getComponent(0)){

			//IF the top of this is higher than the bottom of obj and the bottom of this is further down than the top of obj
			if(position.getComponent(1) < obj.position.getComponent(1) + this.height && this.position.getComponent(1) + this.height > obj.position.getComponent(1)){
				return true;
			}	
		}
		return false;
	}

	/**
	 * Checks whether or not the bounding box surrounding the shape contains a given point
	 * @param xx X position of the point
	 * @param yy Y Position of the point
	 * @return boolean indicating whether the point lies within this gameobject
	 */
	public boolean contains(double xx, double yy){
		return 
				xx < position.getComponent(0) + width && 
				xx > position.getComponent(0) && 
				yy < position.getComponent(1) + height && 
				yy > position.getComponent(1); 
	}

}
