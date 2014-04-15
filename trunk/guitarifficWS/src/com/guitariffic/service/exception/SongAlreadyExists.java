package com.guitariffic.service.exception;

public class SongAlreadyExists extends Exception {
	private static final long serialVersionUID = 1L;

	public SongAlreadyExists(String message) {
		super(message);
	}

}
