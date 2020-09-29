package duke;

import duke.exception.DukeException;
import duke.exception.DukeOutOfBoundsException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

/**
 * Represents the attributes and behaviours of a chat-bot assistant Duke. Each instance of Duke
 * allows one to run and use the chat-bot features such as adding, deleting and managing different
 * kinds of tasks, as well as storing task list to a file.
 */
public class Duke {
    private static Storage storage;
    private static Ui ui;
    public static TaskList taskList;
    public enum TaskType{
        TODO , DEADLINE, EVENT, INVALID
    }

    /**
     * Creates new instances of Ui and Storage classes to enable user interface and access task data from files
     * at fileName and dir specified. Also tries to load data from specified file, in case the file cannot be read-
     * it catches FileNotFoundException and prints error message accordingly. Then proceeds to create a new empty
     * taskList.
     *
     * @param fileName The name and extension of the file where task data will be stored.
     * @param dir The folder in which the task data file will be located.
     */
    public Duke(String fileName, String dir){
        ui = new Ui();
        storage = new Storage(fileName, dir);
        try{
            taskList = new TaskList(storage.readFile());
        }catch(FileNotFoundException e){
            ui.printMessage("\t☹ Could not load the previous data! Because "+e.getMessage());
            taskList = new TaskList();
        }

    }

    /**
     * Runs the main code for the chat-bot Duke. The code loops until the user types "bye." In the event this
     * occurs the variable isNotDone becomes false thereby exiting the loop. The function also catches errors
     * thrown by the objects in the Parser class and displays appropriate error messages.
     */
    protected void run(){
        Scanner in = new Scanner(System.in);
        Parser parse = new Parser(this);
        boolean isNotDone=true;
        String answer;
        ui.printWelcome();
        while(isNotDone){
            answer = in.nextLine();
            try {
                isNotDone = parse.parseCommand(answer);
            }catch (DukeException e){
                ui.printDukeExceptionError();
            }catch (ArrayIndexOutOfBoundsException e){
                ui.printEmptyCommandError();
            }catch (NumberFormatException e){
                ui.printInvalidNumberError();
            }catch (DukeOutOfBoundsException e){
                ui.printRangeError();
            }
        }
        updateFileTasks();
        ui.printFarewell();
    }

    /**
     * Creates a new object of Duke and specifies storage file to be 'duke.txt' under the folder 'data.' Then
     * calls run() to launch the chat-bot assistant Duke.
     *
     * @param args Array of strings taken in when the application is run on the OS with command line.
     */
    public static void main(String[] args) {
       new Duke("duke.txt", "data").run();
    }

    /**
     * Updates the file where tasks are stored at by running updateFile() function of an instance of the
     * class Storage with the latest updated taskList. Catches exception if the files cannot be written to
     * and displays appropriate error message from the Ui class.
     */
    public static void updateFileTasks() {
        try {
            storage.updateFile(taskList.tasks);
        }catch(IOException e){
            ui.printFileError(e);
        }
    }
}
