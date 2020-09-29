package duke;

import duke.exception.DukeOutOfBoundsException;
import duke.exception.EmptyTaskException;
import duke.exception.InvalidFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.util.ArrayList;

/**
 * Represents a single list of tasks stored in an ArrayList<Task> and
 * the different functions used to add, delete, modify the tasks in the list.
 */
public class TaskList {
    public static ArrayList<Task> tasks;
    public static int totalTasksDone=0;
    private Ui ui = new Ui();

    /**
     * Default constructor creates an empty list of tasks.
     */
    public TaskList(){
        tasks = new ArrayList<>();
    }

    /**
     * Parameterised constructor takes in an ArrayList<Task> of tasks and copies it to the current tasks list.
     *
     * @param tasks An ArrayList<Task> containing a list of Task objects.
     */
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    /**
     * Takes in a String and based on the user specifications, deletes a Task at a specified position in the
     * ArrayList<Task>.
     *
     * @param answer A String of the user response/command.
     * @throws DukeOutOfBoundsException If the values specified to be deleted is out of bounds of the Arraylist<Task>
     */
    public void deleteTask(String answer) throws DukeOutOfBoundsException {
        String[] words = answer.trim().split(" ");
        int valueToDelete = Integer.parseInt(words[1]);
        if(valueToDelete<=0 || valueToDelete> tasks.size()) {
            throw new DukeOutOfBoundsException();
        }
        if (tasks.get(valueToDelete-1).getDoneStatus()) {
            totalTasksDone--;
        }
        ui.printRemovedTask(valueToDelete, tasks);
        tasks.remove(valueToDelete-1);
        Duke.updateFileTasks();
    }

    /**
     * Takes in a String and based on user specifications, marks a task at a certain position in the
     * ArrayList<Task> as done.
     *
     * @param answer A String of the user response/command.
     * @throws DukeOutOfBoundsException If value specified to be marked as done is out of range of the ArrayList<Task>
     */
    public void markTaskAsDone(String answer) throws DukeOutOfBoundsException {
        String[] words = answer.trim().split(" ");
        int valueToMarkDone = Integer.parseInt(words[1]);
        if(valueToMarkDone<=0 || valueToMarkDone> tasks.size()) {
            throw new DukeOutOfBoundsException();
        }
        if (!tasks.get(valueToMarkDone-1).getDoneStatus()) {
                totalTasksDone++;
        }
        tasks.get(valueToMarkDone-1).markAsDone();
        ui.printMarkedAsDone(valueToMarkDone, tasks, totalTasksDone);
        Duke.updateFileTasks();
    }

    /**
     * Adds a task to the current ArrayList<Task> tasks based on the taskType passed. Extracts descriptions and
     * time based on the command provided by the user.
     *
     * @param answer A String of the user response/command.
     * @param taskType A Duke.TaskType object specifying the taskType mentioned in tje user's command.
     * @throws EmptyTaskException If nothing is mentioned after the task command. That is total number of words<2.
     * @throws InvalidFormatException If /at or /by is not mentioned in the answer if the command is event or deadline.
     */
    public void addToList(String answer, Duke.TaskType taskType) throws EmptyTaskException, InvalidFormatException {
        String[] words = answer.trim().split(" ");
        if(words.length<2){
            throw new EmptyTaskException();
        }
        answer = answer.trim().substring(answer.trim().indexOf(" "));
        String taskDescription, time;
        switch (taskType){
        case TODO:
            tasks.add(new ToDo(answer));
            break;
        case DEADLINE:
            if(!answer.trim().contains(" /by ")){
                throw new InvalidFormatException();
            }
            taskDescription = answer.substring(0, answer.indexOf("/by"));
            time = answer.substring(answer.indexOf("/by ")+3);
            tasks.add(new Deadline(taskDescription, time));
            break;
        case EVENT:
            if(!answer.trim().contains(" /at ")){
                throw new InvalidFormatException();
            }
            taskDescription = answer.substring(0, answer.indexOf("/at"));
            time = answer.substring(answer.indexOf("/at ")+3);
            tasks.add(new Event(taskDescription, time));
        }
        ui.printAddedTask(tasks);
        Duke.updateFileTasks();
    }
}
