package com.aidanmurphey.todocli.commands;

import com.aidanmurphey.todocli.commands.CommandManager;
import com.aidanmurphey.todocli.tasks.TaskManager;
import com.aidanmurphey.todocli.tasks.Task;
import com.aidanmurphey.todocli.utility.Parsing;
import com.aidanmurphey.todocli.exceptions.TaskNotFoundException;

public class DoneCommand implements Command {

  public int handle(String[] args) throws TaskNotFoundException {
    if (args.length == 0) {
      System.err.println("Invalid arguments! You must provide the index of a task to mark as done");
      return 1;
    }
		int taskIndex = Parsing.stringToInt(args[0]);

    TaskManager instance = TaskManager.getInstance();
		Task task = instance.getTask(taskIndex);

		task.markDone();
    instance.exportToSaveFile();

    return 0;
  }

  public String getDescription() {
    return "Mark an existing task as done";
  }

}
