package com.goodweatherjpa.data;

import com.goodweatherjpa.entities.CustomError;

public class ErrorLogger {

	private String origin;

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public void log(String message) {
		CustomError error = new CustomError();
		error.setOrigin(origin);
		error.setMessage(message);

		System.err.println("----- BEGIN CUSTOM ERROR -----");
		System.err.println("Origin: " + origin);
		System.err.println("Message: " + message);
		System.err.println("----- END CUSTOM ERROR -----");
	}

}
