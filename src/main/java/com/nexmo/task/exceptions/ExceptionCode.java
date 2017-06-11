package com.nexmo.task.exceptions;


public enum ExceptionCode {
	USER_NOT_FOUND("User was not found for the id."),
	GAME_NOT_FOUND("Game was not found for the id."),
	LETTER_NOT_ALPHABETICAL("The letter is not from the alphabet");

	private String message;

	ExceptionCode(String message) {
		this.message = message;
	}

	public String message() {
		return message;
	}

}
