/*******************************************************************************************************
 * @author Daniel J. Sawtelle
 * 
 * Object Node for a puzzle platformer JavaFX application
 ******************************************************************************************************/
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PPObject extends ImageView{
	//Attribute(s)--------------------------------------------------------------------------------------
	private boolean isEnemy = false;
	private boolean isCoin = false;
	private boolean isTeleporter = false;
	
	/***************************************************************************************************
	 * 
	 **************************************************************************************************/
	//Constructor(s)------------------------------------------------------------------------------------
	/***************************************************************************************************
	 * Instantiate a object at the specified location with the provided image information
	 * @param posX as int - The horizontal location initially of the object
	 * @param posY as int - The vertical location initially of the object
	 * @param filePath as String - The file location of the image to be used as the object
	 **************************************************************************************************/
	public PPObject(int posX, int posY, String filePath) {
		super();
		this.setTranslateX(posX);
		this.setTranslateY(posY);
		this.setImage(new Image(filePath));
	}
	
	//Mutator(s)----------------------------------------------------------------------------------------
	/***************************************************************************************************
	 * Set the status of this character as an enemy
	 * @param isEnemy as boolean - Indicates whether this character is an enemy or not
	 **************************************************************************************************/
	public void setIsEnemy(boolean isEnemy) {this.isEnemy = isEnemy;}
	
	//Accessor(s)---------------------------------------------------------------------------------------
	/***************************************************************************************************
	 * Indicates whether this object is an enemy or not
	 * @return boolean - The status of this object as an enemy or not
	 **************************************************************************************************/
	public boolean isEnemy() {return this.isEnemy;}
	
	public void setIsCoin(boolean isCoin){
		this.isCoin=isCoin;
	}
	
	public boolean isCoin() {return this.isCoin;}
	
	
	public void setisTeleporter(boolean isTeleporter){
		this.isTeleporter=isTeleporter;
	}
	
	public boolean isTeleporter() {return this.isTeleporter;}
	
}