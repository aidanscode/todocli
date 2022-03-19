package com.aidanmurphey.todocli.commands;

import com.aidanmurphey.todocli.tasks.TaskManager;
import com.aidanmurphey.todocli.utility.Parsing;  
import com.aidanmurphey.todocli.exceptions.TaskNotFoundException;

public class RemoveCommand implements Command {

  public int handle(String[] args) throws TaskNotFoundException {
    if (args.length == 0) {
      System.err.println("Invalid arguments! You must provide the index of a task to remove");
      return 1;
    }

    int taskIndex = Parsing.stringToInt(args[0]);
    TaskManager instance = TaskManager.getInstance();
		instance.remove(taskIndex);

    instance.exportToSaveFile();

    return 0;
  }

  public String getDescription() {
    return "Remove a task from the todo list";
  }

}
