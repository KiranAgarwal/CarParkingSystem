package com.car.parking;

import com.car.exceptions.InvalidCommandException;
import com.car.model.CarPosition;

/**
 * 
 * @author 
 * Implementation class of the interface ParkingSystem
 * 
 *
 */

public class CarParkingSystem implements ParkingSystem{
	
	private int carParkingWidth;
	
	private int carParkingHeight;
		
	private int carDirection = CAR_DIRECTION_NORTH;
	
	private int cmdErrorsPosition = RESULT_SUCCESS;
	
	private String commandActions ="";
	
	private CarPosition  carPosition; 

	public CarParkingSystem(int carParkingWidth, int carParkingHeight) {
		super();
		this.carParkingWidth = carParkingWidth;
		this.carParkingHeight = carParkingHeight;
		carPosition = new CarPosition(INITIAL_POSITION,INITIAL_POSITION );
	}
	
	public int getCarPositionX(){
		return carPosition.getxPosition();
	}
	public int getCarPositionY(){
		return carPosition.getyPosition();
	}
	public int getCarDirection(){
		return carDirection;
	}
	
	public int getCommandErrorsPosition(){
		return cmdErrorsPosition;
	}
	
	@Override
	public boolean hasCommandError() {
		return cmdErrorsPosition >= 0;
	}

	@Override
	public String getCarPositionString() {
		return carPosition.getxPosition() + "," + carPosition.getyPosition();
	}
	
	// Counting the length of the given commands.
	public int getCommandCount() {
		return commandActions.length();
	}
	
	// Checking the car position specified within the given range
	private boolean checkIfExceeding(CarPosition p) {
		if (p.getxPosition() < 1 || p.getxPosition() > carParkingWidth) {
			return true;
		}

		if (p.getyPosition() < 1 || p.getyPosition() > carParkingHeight) {
			return true;
		}

		return false;
	}
	
	private void setFirstErrorPos(int pos) {
		if (cmdErrorsPosition == RESULT_SUCCESS) {
			cmdErrorsPosition = pos;
		}
	}
	
	// Preparing the car position and commands based on the given input
	public void prepareData(String inputData) {
		
		this.cmdErrorsPosition = RESULT_SUCCESS;

		String positionOfCar = "";
		String instructionsToCar = "";
		
		//Splitting the Car position and commands from the input
		String[] partsOfInputData = inputData.split(":", 2);
		
		if (partsOfInputData.length == 2) {
			positionOfCar = partsOfInputData[0];
			instructionsToCar = partsOfInputData[1];
		} else {
			throw new InvalidCommandException("This command format is not expected");
		}

		int initialX = INITIAL_POSITION;
		int initialY = INITIAL_POSITION;
		
		String[] partsOfCarPosition = positionOfCar.split(",", 2);
		
		//Checking whether the given Car position is valid or not
		if (partsOfCarPosition.length == 2) {
			initialX = Integer.parseInt(partsOfCarPosition[0]);
			initialY = Integer.parseInt(partsOfCarPosition[1]);
		} else {
			throw new InvalidCommandException("Initial Position is wrong");
		}

		this.carPosition = new CarPosition(initialX, initialY);
		this.carDirection = CAR_DIRECTION_NORTH;
		this.commandActions = instructionsToCar;

		if (checkIfExceeding(this.carPosition)) {
			setFirstErrorPos(0);
		}
	}
	
	//Method to execute when Left command is given
	private int turnLeft(int direction) {
		switch (direction) {
		case CAR_DIRECTION_NORTH:
			return CAR_DIRECTION_WEST;
		case CAR_DIRECTION_WEST:
			return CAR_DIRECTION_SOUTH;
		case CAR_DIRECTION_SOUTH:
			return CAR_DIRECTION_EAST;
		case CAR_DIRECTION_EAST:
			return CAR_DIRECTION_NORTH;
		default:
			throw new IllegalStateException("Wrong direction");
		}
	}

	//Method to execute when Right command is given
	private int turnRight(int direction) {
		switch (direction) {
		case CAR_DIRECTION_NORTH:
			return CAR_DIRECTION_EAST;
		case CAR_DIRECTION_WEST:
			return CAR_DIRECTION_NORTH;
		case CAR_DIRECTION_SOUTH:
			return CAR_DIRECTION_WEST;
		case CAR_DIRECTION_EAST:
			return CAR_DIRECTION_SOUTH;
		default:
			throw new IllegalStateException("Wrong direction");
		}
	}
	
	//Method to execute when forward command is given
	private CarPosition moveForword(int direction, CarPosition carLocation) {
		int x = carLocation.getxPosition();
		int y = carLocation.getyPosition();

		switch (direction) {
		case CAR_DIRECTION_NORTH:
			x++;
			break;
		case CAR_DIRECTION_WEST:
			y--;
			break;
		case CAR_DIRECTION_SOUTH:
			x--;
			break;
		case CAR_DIRECTION_EAST:
			y++;
			break;
		default:
			throw new IllegalStateException("Wrong direction");
		}

		return new CarPosition(x, y);
	}
	
	//Execute the commands (Left,Right and forward) based on the input.
	public void executeTheCommands(int count) {
		int commandCountToRun = Math.min(count, commandActions.length());
		
		for (int i = 0; i < commandCountToRun; i++) {
			CarPosition newPosition = carPosition;

			char command = commandActions.charAt(i);
			
			switch (command) {
			case 'L':
				carDirection = turnLeft(carDirection);
				break;
			case 'R':
				carDirection = turnRight(carDirection);
				break;
			case 'F':
				newPosition = moveForword(carDirection, newPosition);
				break;
			default:
				setFirstErrorPos(i + 1);
				throw new InvalidCommandException("Wrong command");
			}

			if (checkIfExceeding(newPosition)) {
				// Only save the first error position, ignore the command
				setFirstErrorPos(i + 1);
			} else {
				carPosition = newPosition;
			}
		}
	}
	
	// This is entry method of executing the given inputs.
	public void executeCommands(String commands) {
		prepareData(commands);
		executeTheCommands(getCommandCount());
	}



	




	
	

	

}
