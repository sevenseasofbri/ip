package duke;

import duke.exception.DukeException;
import duke.exception.DukeOutOfBoundsException;
import duke.exception.EmptyTaskException;
import duke.exception.InvalidFormatException;

/**
 * Represents the functions and attributes of a user response parser. Has functions to read and identify commands
 * based on user response and perform actions accordingly.
 */
public class Parser {
    private Ui ui;
    private TaskList taskList;

    /**
     *  Parameterised constructor that accepts new instance of the chat-bot Duke to store current taskList when
     *  creating a new object of Param. Also initialises the object of Ui to use for user interactions.
     *
     * @param duke The new instance of the Duke class to store current updated taskList.
     */
    public Parser(Duke duke){
        ui = new Ui();
        taskList = duke.taskList;
    }

    /**
     * Takes in a string 'answer' which the user inputs through the command line. Performs checks to identify the
     * commands given. If the command matches 'bye' then the function returns false and the while loop in the run()
     * function in the duke class is terminated, and the program exits. Otherwise, in the case of other commands,
     * the function performs a task and/or prints a response then returns true, to continue running the program.
     *
     * @param answer The user response collected via the command line.
     * @return boolean This is false if 'bye' is read and true for all other commands read.
     * @throws DukeException If command type is INVALID.
     * @throws DukeOutOfBoundsException If an out of range number is specified to mark as done/ delete from the list.
     */
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
            }
        }
        return true;
    }

    /**
     * Returns type of task as an enum value specified in the Duke class. Reads response String passed by
     * parseCommand() and identifies type of task.
     *
     * @param answer Response String from the parseCommand().
     * @return Duke.TaskType An enum with types DEADLINE, EVENT, TODO, INVALID based on type of task read.
     */
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
