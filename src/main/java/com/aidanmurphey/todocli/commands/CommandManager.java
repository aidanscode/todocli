package com.aidanmurphey.todocli.commands;

import java.util.HashMap;
import java.util.Arrays;

import com.aidanmurphey.todocli.exceptions.TaskNotFoundException;
import com.aidanmurphey.todocli.commands.UnknownCommand;
import com.aidanmurphey.todocli.commands.Command;

public class CommandManager {

	private static CommandManager instance;

	private HashMap<String, Command> commands;

	public static CommandManager getInstance() {
		if (instance == null)
			instance = new CommandManager();

		return instance;
	}

	private CommandManager() {
		this.commands = new HashMap<>();
	}

	public void register(String name, Command handler) {
		this.commands.put(name, handler);
	}

	public int handle(String[] args) {
		String name = args.length == 0 ? "help" : args[0];

		Command handler = this.commands.get(name);
		if (handler == null) {
			handler = new UnknownCommand();
		}

		String[] subCommandArgs = args.length < 2 ? (new String[0]) : Arrays.copyOfRange(args, 1, args.length);

		int status;
		try {
			status = handler.handle(subCommandArgs);
		} catch(TaskNotFoundException e) {
			System.err.println(e.getMessage());
			status = 1;
		} catch(Exception e) {
			e.printStackTrace();
			status = 1;
		}

		return status;
	}

	public HashMap<String, Command> getCommands() {
		return this.commands;
	}

}
