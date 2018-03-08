package com.car.exceptions;
/**
 * 
 * @author 
 * User defined exception for the invalid commands
 *
 */

public class InvalidCommandException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidCommandException(String message) {
		super(message);
	}

}
