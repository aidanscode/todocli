package com.aidanmurphey.todocli.commands;

import com.aidanmurphey.todocli.exceptions.TaskNotFoundException;

public class UnknownCommand implements Command {

	public int handle(String[] args) throws TaskNotFoundException {
		System.err.println("Unknown command! Type \"todo help\"");

		return 1;
	}

	//This isn't a registered command so the desc will never show
	//BUT to be compliant with the interface...
	public String getDescription() {
		return "Shows when an unknown command is requested";
	}

}
