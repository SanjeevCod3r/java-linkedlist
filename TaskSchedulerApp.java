import java.util.*;

class Task {
    int taskId;
    String taskName;
    int priority;
    Date dueDate;
    Task next;

    public Task(int taskId, String taskName, int priority, Date dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}

class TaskScheduler {
    private Task head;

    public TaskScheduler() {
        head = null;
    }

    public void addTask(int taskId, String taskName, int priority, Date dueDate) {
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = newTask;
            newTask.next = head; // Circular reference
        } else {
            Task temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newTask;
            newTask.next = head;
        }
    }

    public void removeTask(int taskId) {
        if (head == null) return;

        Task temp = head;
        Task prev = null;
        do {
            if (temp.taskId == taskId) {
                if (prev == null) {
                    // Remove head
                    if (head.next == head) {
                        head = null;
                    } else {
                        prev = head;
                        while (prev.next != head) {
                            prev = prev.next;
                        }
                        head = head.next;
                        prev.next = head;
                    }
                } else {
                    prev.next = temp.next;
                }
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);
    }

    public void displayTasks() {
        if (head == null) {
            System.out.println("No tasks to display.");
            return;
        }

        Task temp = head;
        do {
            System.out.println("Task ID: " + temp.taskId + ", Task Name: " + temp.taskName + ", Priority: " + temp.priority + ", Due Date: " + temp.dueDate);
            temp = temp.next;
        } while (temp != head);
    }

    public Task searchTask(int priority) {
        if (head == null) return null;

        Task temp = head;
        do {
            if (temp.priority == priority) {
                return temp;
            }
            temp = temp.next;
        } while (temp != head);
        return null;
    }
}


public class TaskSchedulerApp {
    public static void main(String[] args) {
        TaskScheduler taskScheduler = new TaskScheduler();
        taskScheduler.addTask(1, "Complete Assignment", 3, new Date());
        taskScheduler.addTask(2, "Buy Groceries", 2, new Date());
        taskScheduler.addTask(3, "Prepare for Exam", 1, new Date());
        taskScheduler.displayTasks();
        taskScheduler.removeTask(2);
        taskScheduler.displayTasks();
    }
}
