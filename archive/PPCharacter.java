/*******************************************************************************************************
 * @author Daniel J. Sawtelle
 * 
 * Character Node for a puzzle platformer JavaFX application
 ******************************************************************************************************/
import java.util.ArrayList;

import javafx.geometry.Point2D;

public class PPCharacter extends PPObject {
	//Attribute(s)--------------------------------------------------------------------------------------
	private int health=0;
	private boolean canJump = true;
	private int coinNum=0;
	private int damage = 0;
	private Point2D velocity = new Point2D(0, 0);
	private ArrayList<PPObject> obstacles;
	
	/***************************************************************************************************
	 * 
	 **************************************************************************************************/
	//Constructor(s)------------------------------------------------------------------------------------
	/***************************************************************************************************
	 * Instantiate a character at the specified location with the provided image information that
	 * interacts with the given obstacle list
	 * @param posX as int - The horizontal location initially of the character
	 * @param posY as int - The vertical location initially of the character
	 * @param filePath as String - The file location of the image to be used as the character
	 * @param obstacles as ArrayList of PPObjects - The obstacles that the character should be aware of
	 **************************************************************************************************/
	public PPCharacter(int posX, int posY, String filePath, ArrayList<PPObject> obstacles) {
		super(posX, posY, filePath);
		this.obstacles = obstacles;
	}

	//Mutator(s)----------------------------------------------------------------------------------------
	/***************************************************************************************************
	 * Set the obstacles list for this character to interact with
	 * @param obstacles as ArrayList of PPObjects - The obstacles that the character should interact with
	 **************************************************************************************************/
	public void setObstacles(ArrayList<PPObject> obstacles) {
		this.obstacles = obstacles;
		this.obstacles.remove(this);	//This character shouldn't consider itself an obstacle
	}
	//Accessor(s)---------------------------------------------------------------------------------------
	/***************************************************************************************************
	 * Return the number of points of damage taken by this character
	 * @param int - The number of points of damage taken by this character so far
	 **************************************************************************************************/
	public int getDamage() {return this.damage;}
	
	//Functional Method(s)------------------------------------------------------------------------------
	/***************************************************************************************************
	 * See if the character should accelerate due to gravity (up to a cap of 10)
	 **************************************************************************************************/
	public void checkVelocity() {if (this.velocity.getY() < 10) {this.velocity = this.velocity.add(0, 1);}}
	/***************************************************************************************************
	 * Initiate a jump of this character
	 **************************************************************************************************/
	public void jump() {
		if (this.canJump) {
			this.velocity = this.velocity.add(0, -30);
			this.canJump = false;
		}
	}
	/***************************************************************************************************
	 * Initiate horizontal movement of this character
	 * @param value as int - The magnitude and direction of the horizontal movement of the character
	 **************************************************************************************************/
	public void moveX(int value) {
		boolean movingRight = value > 0;
		
		for (int i=0; i<Math.abs(value); i++) {
			for (PPObject obstacle : this.obstacles) {
				
				if (this.getBoundsInParent().intersects(obstacle.getBoundsInParent())) {
					if (obstacle.isEnemy()) {
						this.damage = this.damage + 1;
					}
					if (movingRight) {
						if (this.getTranslateX() + this.getImage().getWidth() == obstacle.getTranslateX()) {return;}
					}
					if(obstacle.isCoin()){
						// add coin change here
						
						this.coinNum+=1;
						blah+=1;
						System.out.println(blah);
						
					}
					
					if(obstacle.isEnemy()){
						this.health-=1;
					}
					
					if(obstacle.isTeleporter()){
						this.setTranslateX(this.getTranslateX() -50);	
					}
					
				} else {
					if (this.getTranslateX() == obstacle.getTranslateX() + obstacle.getImage().getWidth()) {return;}
				}
			}
		}
		this.setTranslateX(this.getTranslateX() + (movingRight ? 1 : -1));
	}
	/***************************************************************************************************
	 * Initiate vertical movement of this character
	 * @param value as int - The magnitude and direction of the vertical movement of the character
	 **************************************************************************************************/
	public void moveY(int value) {
		boolean movingDown = value > 0;
		
		for (int i=0; i<Math.abs(value); i++) {
			for (PPObject obstacle : this.obstacles) {
				if (this.getBoundsInParent().intersects(obstacle.getBoundsInParent())) {
					if (obstacle.isEnemy()) {
						this.damage = this.damage + 1;
					}
					if (movingDown) {
						if (this.getTranslateY() + this.getImage().getHeight() == obstacle.getTranslateY()) {
							this.setTranslateY(this.getTranslateY() - 1);
							this.canJump = true;
							return;
						}
					}
				} else {
					if (this.getTranslateY() == obstacle.getTranslateY() + obstacle.getImage().getHeight()) {return;}
				}
			}
		}
		this.setTranslateY(this.getTranslateY() + (movingDown ? 1: -1));
	}
}