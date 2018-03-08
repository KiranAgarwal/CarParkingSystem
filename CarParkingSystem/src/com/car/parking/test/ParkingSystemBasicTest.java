package com.car.parking.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import com.car.parking.*;

/**
 * 
 * @author 
 *
 *This is a test class of CarParkingSystem that provides some basic test scenarios.
 *
 */

public class ParkingSystemBasicTest {
	
	/*public static void main(String[] args) {
		 System.out.println("In main method");
		    JUnitCore jCore = new JUnitCore();
		    jCore.run(ParkingSystemBasicTest.class);
	}*/
	private ParkingSystem parkingSystem;
	
	@Before
	public void setup() {
		parkingSystem = new CarParkingSystem(15, 15);
	}

	// Test case for the commands - 5,5:RFLFRFLF
	@Test
	public void testCase1() {
		parkingSystem.executeCommands("5,5:RFLFRFLF");
		Assert.assertEquals("7,7", parkingSystem.getCarPositionString());
		 System.out.println("Test 1.");
	}

	// Test case for the commands - 6,6:FFLFFLFFLFF
	@Test
	public void testCase2() {
		parkingSystem.executeCommands("6,6:FFLFFLFFLFF");
		Assert.assertEquals("6,6", parkingSystem.getCarPositionString());
		 System.out.println("Test 2.");
	}

	// Test case for the commands - 5,5:FLFLFFRFFF
	@Test
	public void testCase3() {
		parkingSystem.executeCommands("5,5:FLFLFFRFFF");
		Assert.assertEquals("4,1", parkingSystem.getCarPositionString());
		 System.out.println("Test 3.");
	}

	// Test case when no command is given. Only position is specified as an input.
	@Test
	public void testNoCommandsGiven() {
		parkingSystem.executeCommands("1,1:");
		Assert.assertEquals("1,1", parkingSystem.getCarPositionString());
		
		parkingSystem.executeCommands("15,15:");
		Assert.assertEquals("15,15", parkingSystem.getCarPositionString());
	}
	
	//// Test case when wrong position is specified as an input
	@Test
	public void testNoCommandsWithWrongPosition() {
		parkingSystem.executeCommands("0,0:");
		Assert.assertTrue(parkingSystem.hasCommandError());

		parkingSystem.executeCommands("16,16:");
		Assert.assertTrue(parkingSystem.hasCommandError());
	}


	
	

}
