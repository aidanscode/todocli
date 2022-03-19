package com.aidanmurphey.todocli.commands;

import com.aidanmurphey.todocli.exceptions.TaskNotFoundException;
import com.aidanmurphey.todocli.commands.Command;
import com.aidanmurphey.todocli.tasks.TaskManager;
import com.aidanmurphey.todocli.tasks.Task;
import java.util.LinkedList;

public class ListCommand implements Command {

	public int handle(String[] args) throws TaskNotFoundException {
		LinkedList<Task> tasks = TaskManager.getInstance().getTasks();

		int index = 0;
		String doneString = "(done)";
		String notDoneString = "";

		System.out.println("All saved tasks:");
		for (Task task : tasks) {
			System.out.println((index++) + ": " + (task.isDone() ? doneString : notDoneString) + task.getDetails());
		}

		return 0;
	}

	public String getDescription() {
		return "Lists all saved tasks";
	}

} 
