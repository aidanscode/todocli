package com.aidanmurphey.todocli.exceptions;

public class TaskNotFoundException extends Exception {

	public TaskNotFoundException(int index) {
		super("No task found at index: " + index);
	}

} 
