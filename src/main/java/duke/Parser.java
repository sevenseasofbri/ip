package duke;

import duke.exception.DukeException;
import duke.exception.DukeOutOfBoundsException;
import duke.exception.EmptyTaskException;
import duke.exception.InvalidFormatException;

import java.time.DateTimeException;
import java.time.format.DateTimeParseException;

public class Parser {
    private Ui ui;
    private TaskList taskList;
    public Parser(Duke duke){
        ui = new Ui();
        taskList = duke.taskList;
    }
    boolean parseCommand(String answer) throws DukeException, DukeOutOfBoundsException {
        if(answer.trim().equalsIgnoreCase("bye")){
            return false;
        }
        if(answer.trim().equalsIgnoreCase("list")){
            ui.printList(taskList.tasks);
            return true;
        }
        if(answer.trim().matches("done(.*)")){
            taskList.markTaskAsDone(answer);
        }else if(answer.trim().matches("delete(.*)")){
            taskList.deleteTask(answer);
        } else {
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
            }catch (InvalidFormatException e){
                ui.printFormatError();
            }catch (DateTimeParseException e){
                ui.printFormatError();
            }catch (DateTimeException e){
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
