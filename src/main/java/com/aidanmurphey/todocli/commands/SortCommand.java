package com.aidanmurphey.todocli.commands;

import com.aidanmurphey.todocli.tasks.TaskManager;
import com.aidanmurphey.todocli.utility.Parsing;
import com.aidanmurphey.todocli.exceptions.TaskNotFoundException;

public class SortCommand implements Command {

	public int handle(String[] args) throws TaskNotFoundException {
		TaskManager instance = TaskManager.getInstance();
		instance.sort();
		instance.exportToSaveFile();

		return 0;
	}

	public String getDescription() {
		return "Reorder the task list, putting all unfinished tasks first";
	}

}
