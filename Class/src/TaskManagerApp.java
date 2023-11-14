import java.io.*;
import java.util.*;

class Task implements Comparable<Task> {
    private String title;
    private String description;
    private boolean isCompleted;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.isCompleted = false;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public int compareTo(Task otherTask) {
        return 0;
    }
}

class PriorityTask extends Task {
    private String priority;

    public PriorityTask(String title, String description, String priority) {
        super(title, description);
        this.priority = priority;
    }

}

class ImmutableTask {
    private final String title;
    private final String description;
    private final boolean isCompleted;

    public ImmutableTask(String title, String description, boolean isCompleted) {
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
    }

}

class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public void changeTaskStatus(Task task) {
        task.setCompleted(!task.isCompleted());
    }

    public void saveTasksToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadTasksFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            tasks = (List<Task>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

public class TaskManagerApp {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("Task 1", "Description 1");
        PriorityTask priorityTask = new PriorityTask("Task 2", "Description 2", "High");

        taskManager.addTask(task1);
        taskManager.addTask(priorityTask);


        taskManager.saveTasksToFile("tasks.ser");
        taskManager.loadTasksFromFile("tasks.ser");

    }
}