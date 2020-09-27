package duke;

import duke.exception.*;

import java.time.DateTimeException;
import java.time.format.DateTimeParseException;

public class Parser {
    private Ui ui;
    private TaskList taskList;
    public Parser(Duke duke){
        ui = new Ui();
        taskList = duke.taskList;
    }
    boolean parseCommand(String answer) throws DukeException, DukeOutOfBoundsException, FindFormatException {
        if(answer.trim().equalsIgnoreCase("bye")){
            return false;
        }
        if(answer.trim().equalsIgnoreCase("list")){
            ui.printList(taskList.tasks, false);
            return true;
        }
        if(answer.trim().matches("done(.*)")){
            taskList.markTaskAsDone(answer);
        }else if(answer.trim().matches("delete(.*)")){
            taskList.deleteTask(answer);
        }else if(answer.trim().matches("find(.*)")){
            taskList.findTasksWithKeyword(answer);
        }
        else {
            Duke.TaskType taskType = getTaskType(answer);
            if(taskType == Duke.TaskType.INVALID){
                throw new DukeException();
            }
            try {
                taskList.addToList(answer, taskType);
            }catch(EmptyTaskException e){
                ui.printEmptyDescriptionError(taskType);
            }catch(StringIndexOutOfBoundsException e){
                ui.printOutOfBoundError();
            } catch (InvalidFormatException | DateTimeException e){
                ui.printFormatError();
            }
        }
        return true;
    }

    private static Duke.TaskType getTaskType(String answer) {
        Duke.TaskType taskType;
        answer = answer.trim();
        if(answer.matches("deadline(.*)")){
            taskType = Duke.TaskType.DEADLINE;
        } else if(answer.matches("event(.*)")){
            taskType = Duke.TaskType.EVENT;
        } else if(answer.matches("todo(.*)")){
            taskType = Duke.TaskType.TODO;
        } else{
            taskType = Duke.TaskType.INVALID;
        }
        return taskType;
    }
}
