package duke;

import duke.task.Task;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents different messages to print out to the user by Duke.
 */
public class Ui {
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String LINE_BREAK = "\t____________________________________________________________";
    public static final String YOUR_LIST = "\t_______________________~Your List~__________________________";
    public static final String TASKS_FOUND="\t______________________~Tasks Found~_________________________";


    /**
     * Prints the message passed in the format which Duke responds with.
     *
     * @param message A String of the message to be printed.
     */
    public void printMessage(String message){
        System.out.println(LINE_BREAK);
        System.out.println(message);
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints the welcome message.
     */
    public void printWelcome(){
        printMessage("\tHello! I'm\n" + Ui.LOGO + "\n\tWhat can I do for you?\uD83D\uDE0A\n"+
                    "\tTo better understand my capabilities, type 'help' and then press enter ✨");
    }

    /**
     * Prints the farewell message.
     */
    void printFarewell() {
        printMessage("\tFarewell. Until next time my dude.");
    }

    /**
     * Prints the error that occurs when loading a file.
     *
     * @param e An IOException type which is used to get error message.
     */
    void printFileError(IOException e) {
        printMessage("\tSomething went wrong while loading file: "+ e.getMessage());
    }

    /**
     * Prints the ArrayList<Task> passed to it in a manner specific to Duke.
     * @param tasks An ArrayList<Task> to be printed.
     * @param isForFind A boolean value that specifies if the print action is for the find command or not.
     */
    public void printList(ArrayList<Task> tasks, boolean isForFind) {
        System.out.println(isForFind ? TASKS_FOUND : YOUR_LIST);
        int index = 0;
        for(Task task: tasks) {
            System.out.println("\t" + (index + 1) + ". " + task);
            index++;
        }
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints the error caused by DukeException.
     */
    public void printDukeExceptionError() {
        printMessage("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Prints error caused by an empty command.
     */
    public void printEmptyCommandError() {
        printMessage("\t☹ Empty command! Please specify the task number to mark as complete/delete.");
    }

    /**
     * Prints the error caused by entering a non numeric character after the delete/done commands.
     */
    public void printInvalidNumberError() {
        printMessage("\t☹ Sorry could not mark as done/deleted! Please enter a valid number.");
    }

    /**
     * Prints the error caused by entering a number out of range of the taskList in Duke.
     */
    public void printRangeError() {
        printMessage("\t☹ Cannot mark task as done/deleted! :(\n\tPlease enter a list number within the range.");
    }

    /**
     * Prints the error caused by empty description when using task commands todo/deadline/event.
     * @param taskType A Duke.TaskType enum that specifies the type of task passed to be printed.
     */
    public void printEmptyDescriptionError(Duke.TaskType taskType) {
        printMessage("\t☹ OOPS!!! The description of a "+
                    (taskType == Duke.TaskType.TODO?"todo":(taskType == Duke.TaskType.DEADLINE?"deadline":"event"))+
                    " cannot be empty.");
    }

    /**
     * Prints error caused by not setting a time for a deadline/event.
     */
    public void printOutOfBoundError() {
        printMessage("\t☹ Please start with the command with todo/deadline/event!"+
                    "\n\t OR please specify a date/time if you want to set a deadline/event!");
    }

    /**
     * Prints error caused by entering an invalid format.
     */
    public void printFormatError() {
        printMessage("\t☹ Remember to format your command like this ⬇" +
                    "\n\t todo <description>" +
                    "\n\t deadline <description> /by <yyyy-mm-dd>" +
                    "\n\t event <description> /at <yyyy-mm-dd>");
    }

    /**
     * Prints the task removed using the delete operation.
     * @param valueToDelete An int that specifies the task number to be deleted.
     * @param tasks The original ArrayList<Task> of tasks before the task specified is deleted.
     */
    public void printRemovedTask(int valueToDelete, ArrayList<Task> tasks) {
        printMessage("\tNoted! I've removed this task: \n\t" +
                    tasks.get(valueToDelete - 1)+
                    "\n\tNow you have " +(tasks.size() - 1)+(tasks.size() - 1 != 1?" tasks":" task")+" in the list :D");
    }

    /**
     * Prints the task marked done as well as the number of tasks left to mark as done.
     * @param valueToMarkDone An int that specifies the task number to mark as done.
     * @param tasks An ArrayList<Task> of the tasks.
     * @param totalTasksDone An int that contains the total number of tasks done.
     */
    public void printMarkedAsDone(int valueToMarkDone, ArrayList<Task> tasks, int totalTasksDone) {
        printMessage("\tAwesome! I've marked this task as done:"+"\n\t" +
                      tasks.get(valueToMarkDone - 1)+
                      "\n\tOnly " + (tasks.size() - totalTasksDone) + " to go! ;)");
    }

    /**
     * Prints the most recent task added to the list.
     * @param tasks An ArrayList<Task> specifying the users current saved tasks.
     */
    public void printAddedTask(ArrayList<Task> tasks) {
        printMessage("\tAdded:" + tasks.get(tasks.size() - 1) +
                    "\n\tNow you have "+ tasks.size()+(tasks.size() != 1?" tasks" : " task") + " in the list :D");
    }

    /**
     * Prints error if the format of the find command is wrong.
     */
    public void printFindFormatError() {
        printMessage("\t☹ Please remember to specify a keyword to search for!");
    }

    /**
     * Prints out a command summary.
     */
    public void printHelp() {
        System.out.println(LINE_BREAK);
        System.out.println("\tHere's a command summary to help you out! You may also refer to " +
                            "sevenseasofbri.github.io/ip for the User Guide ;)");
        System.out.println("\tCOMMAND SUMMARY\n" +
                            "\tNote: Text in \"<>\" are parameters to be specified by you!\n" +
                            "\t1. Add a to-do task: todo <description>\n" +
                            "\t2. Add a deadline: deadline <description> /by <yyyy-mm-dd>\n" +
                            "\t3. Add an event: event <description> /at <yyyy-mm-dd>\n" +
                            "\t4. List all tasks: list\n" +
                            "\t5. Mark a task as done: done <task number>\n" +
                            "\t6. Delete a task: delete <task number>\n" +
                            "\t7. Locate a task by a keyword: find <keyword>\n" +
                            "\t8. Exit the application: bye");
        System.out.println(LINE_BREAK);
    }
}
