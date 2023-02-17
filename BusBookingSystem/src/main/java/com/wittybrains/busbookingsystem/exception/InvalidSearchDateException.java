package com.wittybrains.busbookingsystem.exception;


public class InvalidSearchDateException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidSearchDateException(String message) {
        super(message);
    }
}

