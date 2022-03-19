package com.aidanmurphey.todocli.commands;

import com.aidanmurphey.todocli.commands.CommandManager;
import com.aidanmurphey.todocli.tasks.TaskManager;
import com.aidanmurphey.todocli.exceptions.TaskNotFoundException;

public class AddCommand implements Command {

	public int handle(String[] args) throws TaskNotFoundException {
		if (args.length == 0) {
			System.err.println("Invalid arguments! You must provide at least once task to add");
			return 1;
		}

		TaskManager instance = TaskManager.getInstance();
		for(String newTask : args)
			instance.add(newTask);
		instance.exportToSaveFile();

		return 0;
  }

	public String getDescription() {
		return "Add a new task to the todo list";
	}

}
