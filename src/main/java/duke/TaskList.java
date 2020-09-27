package duke;

import duke.exception.DukeOutOfBoundsException;
import duke.exception.EmptyTaskException;
import duke.exception.InvalidFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> tasks;
    public static int totalTasksDone=0;
    private Ui ui = new Ui();
    public TaskList(){
        tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }
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
