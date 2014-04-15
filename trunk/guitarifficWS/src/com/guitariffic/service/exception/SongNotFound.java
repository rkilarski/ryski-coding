package com.guitariffic.service.exception;

public class SongNotFound extends Exception {
	private static final long serialVersionUID = 1L;

	public SongNotFound(String message) {
		super(message);
	}

}
