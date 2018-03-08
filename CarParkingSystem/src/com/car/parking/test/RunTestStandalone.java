package com.car.parking.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.internal.TextListener;

public class RunTestStandalone {

	public static void main(String[] args) {
		   JUnitCore junit = new JUnitCore();
		    //Result result = JUnitCore.runClasses(ParkingSystemBasicTest.class);
		   Result result = junit.run(ParkingSystemBasicTest.class);
		    junit.addListener(new TextListener(System.out));
		      for (Failure failure : result.getFailures()) {
		         System.out.println(failure.toString());
		         System.out.println("Test failed.");
		      }
		      System.out.println(result.wasSuccessful());
		      System.out.println("Test Passed.");
		      System.exit(0);
		   }
	}

