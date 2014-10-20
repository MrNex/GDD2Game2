package loader;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import objects.GameObject;
import triggers.BounceTrigger;
import levels.Level;
import mathematics.Vec;

public class LevelLoader extends Loader<Level> {

	public LevelLoader() {
		super("./Assets/Levels", ".png");
	}

	@Override
	public HashMap<String, Level> loadAll() {
		//Create hashmap of levels
		HashMap<String, Level> levels = new HashMap<String, Level>();
		
		//for each file in the directory which has the proper extension
		for(File f : getDirectory().listFiles(
			new FilenameFilter(){

				@Override
				public boolean accept(File arg0, String arg1) {
					return arg1.endsWith(ext);
				}
					
			}
		)){
			//Store the filename
			String fileName = f.getName();
			//Create key for hashmap
			String key = fileName.substring(0, fileName.length() - ext.length());
			
			//Declare image to load level from
			BufferedImage levelPicture;
			
			//Try to loadimage
			try {
				levelPicture = ImageIO.read(f);
			} catch(IOException e) {
				//If shit goes down, print error
				e.printStackTrace();
				System.out.println(e.getMessage());
				levelPicture = null;
				
				//Move to next file
				continue;
			}
			
			//Create level
			Level thisLevel = new Level();
			
			//Get level width and height and num pixels
			int width = levelPicture.getWidth();
			int height = levelPicture.getHeight();
			
						
			//Set variables to help build game objects out of pixels
			boolean spanningObject = false;
			int indexObjStart = 0;
			Color previousColor = Color.white;
			
			//For each row of pixels
			for(int i = 0; i < height; i++){
				//For each column of pixels
				for(int j = 0; j < width; j++){
					Color c = new Color(levelPicture.getRGB(i, j));
					
					//If the color is white
					if(c.equals(Color.white)){
						if(!previousColor.equals(Color.white)){
							//Create object
							thisLevel.addObject(makeObject(i, indexObjStart, j, previousColor));
						}
					}
					else if(!previousColor.equals(c)){
						if(!previousColor.equals(Color.white)){
							thisLevel.addObject(makeObject(i, indexObjStart, j, previousColor));
						}
						
						indexObjStart =j;
						
					}	
					previousColor = c;
				}
				if(!previousColor.equals(Color.white)){
					thisLevel.addObject(makeObject(i, indexObjStart, width, previousColor));
				}
				previousColor = Color.white;
			}
			
			//Add level to hashmap
			levels.put(key, thisLevel);
		}
		
		return levels;
	}
	
	/**
	 * 
	 * @param pixelRow
	 * @param startIndex
	 * @param pixelColumn
	 * @param objColor
	 * @return
	 */
	private GameObject makeObject(int pixelRow, int startIndex, int pixelColumn, Color objColor){
		//Get obj xPos
		double x = startIndex * 20;
		double y = pixelRow * 20;
		
		//Get dimensions
		double w = (pixelColumn - startIndex) * 20;
		double h = 20;
		
		//Create object
		GameObject obj = makeWall(x, y, w, h, new Vec(1, 0));
		if(objColor.equals(Color.red)){
			obj.setTriggerable(true);
			obj.addTrigger(new BounceTrigger());
			obj.setColor(Color.red);
		}
		return obj;
	}
	
	/**
	 * Builds a basic wall gameobject
	 * @param x position in world
	 * @param y position in world
	 * @param w width of rect to make
	 * @param h height of rect to make
	 * @param Forward vector (1, 0) if not rotated.
	 * @return A gameobject representing  wall
	 */
	private GameObject makeWall(double x, double y, double w, double h, Vec v){
		GameObject obj = new GameObject(x,y,w,h,v);
		obj.setShape(new Rectangle2D.Double());
		obj.setVisible(true);
		obj.setColor(Color.black);
		return obj;
	}
	

}
