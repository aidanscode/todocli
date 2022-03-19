package com.aidanmurphey.todocli.tasks;

import com.aidanmurphey.todocli.exceptions.TaskNotFoundException;
import com.aidanmurphey.todocli.tasks.Task;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.io.File;

public class TaskManager {

	private static TaskManager instance;

	private LinkedList<Task> tasks;

	public static TaskManager getInstance() {
		if (instance == null)
			instance = new TaskManager();

		return instance;
	}

	private TaskManager() {
		this.tasks = new LinkedList<>();
		this.importFromSaveFile();
	}

	private void importFromSaveFile() {
		File saveFile = this.getSaveFile();
		BufferedReader br = null;
		try {
			FileReader fr = new FileReader(saveFile);
			br = new BufferedReader(fr);

			String line;
			while ((line = br.readLine()) != null) {
				if (line.charAt(1) != ',')
					continue;
				char doneFlag = line.charAt(0);
				String details = line.substring(2);
	
				Task task = new Task(details);
				if (doneFlag == '1')
					task.markDone();
				this.tasks.add(task);
			}
		} catch(IOException e) {
			e.printStackTrace();
			System.exit(2);
			return;
		} finally {
			try {
				br.close();
			} catch(IOException e) {
				e.printStackTrace();
				System.exit(2);
			}
		}
	}

	public void exportToSaveFile() {
		File saveFile = this.getSaveFile();
		String filePath = saveFile.getAbsolutePath();

		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(filePath);

			for (Task task : this.tasks) {
				fileWriter.write(task.isDone() ? "1" : "0");
				fileWriter.write("," + task.getDetails() + "\n");
			}
		} catch(IOException e) {
			e.printStackTrace();
			System.exit(2);
			return;
		} finally {
			try {
				if (fileWriter != null)
					fileWriter.close();
			} catch(IOException e) {
				e.printStackTrace();
				System.exit(2);
			}
		}
	}

	private File getSaveFile() {
		String path = System.getProperty("user.home") + "/.todocli", fileName = "/save.csv";
		File pathFile = new File(path);
		File fileFile = new File(path + fileName);

		if (!fileFile.exists()) {
			try {
				pathFile.mkdirs();
				fileFile.createNewFile();
			} catch(IOException e) {
				e.printStackTrace();
				System.exit(2);
				return null;
			}
		}

		return fileFile;
	}

	public LinkedList<Task> getTasks() {
		return this.tasks;
	}

	public Task getTask(int index) throws TaskNotFoundException {
		if (this.tasks.size() < (index + 1)) {
			throw new TaskNotFoundException(index);
		}

		return this.getTasks().get(index);
	}

	public void add(String details) {
		this.tasks.add(new Task(details));
	}

	public void remove(int index) throws TaskNotFoundException {
		//we don't actually want it, but to confirm it exists:
		this.getTask(index);

		this.tasks.remove(index);
	}

	public void markDone(int index) throws TaskNotFoundException {
		Task task = this.getTask(index);

		task.markDone();
	}

	public void sort() {
		int total = this.tasks.size();
		Task[] notDone = new Task[total];
		Task[] done = new Task[total];

		int notDoneIndex = 0, doneIndex = 0;

		for(Task task : this.tasks) {
			if (task.isDone())
				done[doneIndex++] = task;
			else
				notDone[notDoneIndex++] = task;
		}

		LinkedList<Task> newTasksList = new LinkedList<>();
		for(int i = 0; i < notDone.length; i++)
			if (notDone[i] != null)
				newTasksList.add(notDone[i]);
			else
				break;

		for(int i = 0; i < done.length; i++)
			if (done[i] != null)
				newTasksList.add(done[i]);
			else
				break;

		this.tasks = newTasksList;
	}

}
