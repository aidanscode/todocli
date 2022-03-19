package com.aidanmurphey.todocli.commands;

import com.aidanmurphey.todocli.commands.CommandManager;
import com.aidanmurphey.todocli.exceptions.TaskNotFoundException;
import java.util.HashMap;

public class HelpCommand implements Command {

	public int handle(String[] args) throws TaskNotFoundException {
		HashMap<String, Command> commands = CommandManager.getInstance().getCommands();

		System.out.println("TODO-cli help menu:");
		for (String name : commands.keySet()) {
			System.out.println("\ttodo " + name + ": " + commands.get(name).getDescription());
		}

		return 0;
	}

	public String getDescription() {
		return "List all TODO-cli commands and their descriptions";
	}

}
