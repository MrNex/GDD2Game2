package engine.manager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import objects.GameObject;
import engine.Engine;

/**
 * A {@link Manager} which takes care of drawing objects in
 * the engines current state to the screen. This includes camera
 * transformations and such other rendering related tasks.
 * @author Nex
 *
 */
public class ScreenManager extends Manager{

	//Attributes
	private JFrame window;
	private JPanel drawPanel;
	private Color backgroundColor;
	private int width, height;
	


	/**
	 * Constructs a ScreenManager
	 */
	public ScreenManager() {
		super();
	}
	
	//Accessors
	/**
	 * Get the window the program is running in
	 * @return The window containing the program
	 */
	public JFrame getWindow(){
		return window;
	}

	/**
	 * Initializes all member variables of ScreenManager.
	 * Sets width and height of viewport,
	 * Creates the drawPanel with the drawing instructions
	 * TODO: Attaches input manager
	 */
	@Override
	public void init() {
		//Set internals
		width = 800;
		height = 600;

		//Create the window
		window = new JFrame("Mathemancy V 0.1");
		window.setSize(width, height);

		//Create the panel
		drawPanel = new JPanel(){

			/**
			 * Set the drawing instructions for the panel
			 */
			@Override
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				//TODO: Change refreshing to real time
				//Refresh screen
				//super.setBackground(null);
				g.setColor(backgroundColor);
				g.fillRect(0, 0, width, height);

				//Cast to graphics2d
				Graphics2D g2d = (Graphics2D)g;



				ArrayList<GameObject> drawList = Engine.currentInstance.getCurrentState().getObjList();

				//For every game object in objects
				for(GameObject obj : drawList){
					obj.draw(g2d);
				}
			}
		};

		drawPanel.setPreferredSize(new Dimension(800, 600));

		//window.setContentPane(this);
		window.add(drawPanel);

		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Set the background
		backgroundColor = Color.white;
	}

	/**
	 * Paints the screen
	 */
	@Override
	public void update() {
		drawPanel.repaint();
	}

}
