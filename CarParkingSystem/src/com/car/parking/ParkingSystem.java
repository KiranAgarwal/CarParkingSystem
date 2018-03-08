package com.car.parking;
/**
 * 
 * @author 
 * 
 * This interface contains initials  positions and car directions
 * Contains methods to execute the commands given as an input.
 *
 */

public interface ParkingSystem extends CarParkingPosition {
	

	int getCarPositionX();

	int getCarPositionY();

	int getCarDirection();

	int getCommandErrorsPosition();
	
	String getCarPositionString();

	void executeCommands(String commands);

	boolean hasCommandError();

	void prepareData(String input);

	int getCommandCount();

	void executeTheCommands(int count);

}
