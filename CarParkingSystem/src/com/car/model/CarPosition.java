package com.car.model;

/**
 * 
 * @author
 * 
 * This class contains the position of the car.
 *
 */
public class CarPosition {
	
	private int xPosition;
	
	public CarPosition(){
		this.xPosition = -1;
		this.yPosition = -1;
	}
	
	public CarPosition(int xPosition, int yPosition) {
		super();
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}

	private int yPosition;

	public int getxPosition() {
		return xPosition;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

}
