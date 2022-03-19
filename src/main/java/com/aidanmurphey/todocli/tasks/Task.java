package com.aidanmurphey.todocli.tasks;

public class Task {

	private String details;
	private boolean isDone;

	public Task(String details) {
		this.details = details;
		this.isDone = false;
	}

	public String getDetails() {
		return this.details;
	}

	public boolean isDone() {
		return this.isDone;
	}

	public void markDone() {
		this.isDone = true;
	}

}
