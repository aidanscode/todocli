package com.aidanmurphey.todocli.commands;

import com.aidanmurphey.todocli.exceptions.TaskNotFoundException;

public interface Command {

	int handle(String[] args) throws TaskNotFoundException;

	String getDescription();

}
