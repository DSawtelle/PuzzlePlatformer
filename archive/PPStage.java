/*******************************************************************************************************
 * @author Daniel J. Sawtelle/Aaron J. Trevino/Ping Hsu
 * 
 * Stage creation class given a level file and the asset conversion of the characters in the file
 ******************************************************************************************************/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import javafx.scene.layout.Pane;

public class PPStage extends Pane{
	//Attribute(s)--------------------------------------------------------------------------------------
	private HashMap<Character, String> assets;
	private int levelWidth;
	private int levelHeight;
	private static final int BLOCK_SIZE  = 32;
	
	//Constructor(s)------------------------------------------------------------------------------------
	/***************************************************************************************************
	 * Setup a new stage using a HashMap of assets (image file locations and their character representation)
	 * @param assets as HashMap of Character, String - The characters to interpret as the given image paths
	 * @param filePath as String - The file to attempt to open and interpret as the given image
	 **************************************************************************************************/
	public PPStage(HashMap<Character, String> assets, String filePath) throws FileNotFoundException{
		this.assets = assets;
		
		//Read the map text file and build the stage based on it's contents
		Scanner mapFile = new Scanner(new File(filePath));
		String firstLine = mapFile.nextLine();
		this.levelWidth = firstLine.length() * BLOCK_SIZE;
		int y = 0;
		while(mapFile.hasNextLine()) {
			Character[] mapLine = mapFile.nextLine().chars().mapToObj(c -> (char)c).toArray(Character[]::new);
			for(int x = 0; x < mapLine.length; x++) {
				drawBlock(mapLine[x], x, y);
			}
			y++;
		}
		this.levelHeight = y+1;
		mapFile.close();
	}
	
	//Mutator(s)---------------------------------------------------------------------------------------
	
	//Accessor(s)---------------------------------------------------------------------------------------
	/***************************************************************************************************
	 * Get the height of this build level
	 * @return int - Height of the given level in number of objects
	 **************************************************************************************************/
	public int getLevelHeight() {return this.levelHeight;}
	/***************************************************************************************************
	 * Get the width of the given level
	 * @return int - Width of the given level in number of objects
	 **************************************************************************************************/
	public int getLevelWidth() {return this.levelWidth;}
	
	//Functional Method(s)------------------------------------------------------------------------------
	/***************************************************************************************************
	 * Build the block given the character as the passed location in the level file
	 * @param object as Character - The specifier of what object is at the given location in the level
	 * @param x as int - The location horizontally of the object to be drawn
	 * @param y as int - the vertical location of the object to be drawn
	 **************************************************************************************************/
	private void drawBlock(Character object, int x, int y) {
		//TODO - Determine how to allow characters/objects/interactives to be separately instantiated as children.
		if (this.assets.containsKey(object)) {
			this.getChildren().add(new PPObject(x*BLOCK_SIZE, y*BLOCK_SIZE, this.assets.get(object)));
		}
	}
}