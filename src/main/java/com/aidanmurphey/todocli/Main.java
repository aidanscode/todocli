package com.aidanmurphey.todocli;

import com.aidanmurphey.todocli.commands.*;

class Main {

  public static void main(String[] args) {
		Main.init();

    int statusCode = CommandManager.getInstance().handle(args);
		System.exit(statusCode);
	}

	private static void init() {
		CommandManager cmdManager = CommandManager.getInstance();
		cmdManager.register("help", new HelpCommand());
		cmdManager.register("list", new ListCommand());
		cmdManager.register("add", new AddCommand());
		cmdManager.register("done", new DoneCommand());
		cmdManager.register("rm", new RemoveCommand());
		cmdManager.register("sort", new SortCommand());
	}

}
