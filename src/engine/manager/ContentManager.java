package engine.manager;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import loader.ImageLoader;
import loader.Loader;

/**
 * Class defines a component of the engine which manages all content and assets
 * @author Nex
 *
 */
public class ContentManager extends Manager {

	//Attributes
	private HashMap<String, BufferedImage> images;
	
	/**
	 * Constructs a content manager
	 */
	public ContentManager() {
		super();
	}
	
	//Accessors
	/**
	 * Gets an image by name from images hashmap
	 * @param imageName the desired image's name
	 * @return The image with the name that was specified
	 */
	public BufferedImage getImage(String imageName){
		return images.get(imageName);
	}
	
	//Methoids

	/**
	 * Initializes all member variables
	 */
	@Override
	public void init() {
		//Declare loader variable
		Loader loader;
		loader = new ImageLoader();
		
		images = loader.loadAll();

	}

	/**
	 * Updates the content manager
	 * This does not need to be updated, I don't think.
	 * So this will probably remain empty.
	 */
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
