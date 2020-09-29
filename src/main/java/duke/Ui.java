package duke;

import duke.task.Task;
import java.io.IOException;
import java.util.ArrayList;

public class Ui {
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String LINE_BREAK = "\t____________________________________________________________";
    public static final String YOUR_LIST = "\t_______________________~Your List~__________________________";

    public void printMessage(String message){
        System.out.println(LINE_BREAK);
        System.out.println(message);
        System.out.println(LINE_BREAK);
    }

    public void printWelcome(){
        printMessage("\tHello! I'm\n"+ Ui.LOGO+"\n\tWhat can I do for you?\uD83D\uDE0A");
    }

    void printFarewell() {
        printMessage("\tFarewell. Until next time my dude.");
    }

    void printFileError(IOException e) {
        printMessage("\tSomething went wrong while loading file: "+ e.getMessage());
    }

    public void printList(ArrayList<Task> tasks){
        System.out.println(YOUR_LIST);
        int index =0;
        for(Task task: tasks){
            System.out.println("\t"+(index+1)+". "+ task);
            index++;
        }
        System.out.println(LINE_BREAK);
    }

    public void printDukeExceptionError() {
        printMessage("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void printEmptyCommandError() {
        printMessage("\t☹ Empty command! Please specify the task number to mark as complete/delete.");
    }

    public void printInvalidNumberError() {
        printMessage("\t☹ Sorry could not mark as done/deleted! Please enter a valid number.");
    }
    public void printRangeError() {
        printMessage("\t☹ Cannot mark task as done/deleted! :(\n\tPlease enter a list number within the range.");
    }
    public void printEmptyDescriptionError(Duke.TaskType taskType) {
        printMessage("\t☹ OOPS!!! The description of a "+
                    (taskType == Duke.TaskType.TODO?"todo":(taskType == Duke.TaskType.DEADLINE?"deadline":"event"))+
                    " cannot be empty.");
    }
    public void printOutOfBoundError() {
        printMessage("\t☹ Please start with the command with todo/deadline/event!"+
                    "\n\t OR please specify a date/time if you want to set a deadline/event!");
    }
    public void printFormatError() {
        printMessage("\t☹ Remember to format your command like this ⬇" +
                    "\n\t todo <description>"+
                    "\n\t deadline <description> /by <yyyy-mm-dd>"+
                    "\n\t event <description> /at <yyyy-mm-dd>");
    }

    public void printRemovedTask(int valueToDelete, ArrayList<Task> tasks) {
        printMessage("\tNoted! I've removed this task: \n\t" +
                    tasks.get(valueToDelete -1)+
                    "\n\tNow you have " +(tasks.size()-1)+(tasks.size()-1!=1?" tasks":" task")+" in the list :D");
    }

    public void printMarkedAsDone(int valueToMarkDone, ArrayList<Task> tasks, int totalTasksDone) {
        printMessage("\tAwesome! I've marked this task as done:"+"\n\t" +
                      tasks.get(valueToMarkDone -1)+
                      "\n\tOnly " + (tasks.size() - totalTasksDone) + " to go! ;)");
    }

    public void printAddedTask(ArrayList<Task> tasks) {
        printMessage("\tAdded:" + tasks.get(tasks.size()-1) +
                        "\n\tNow you have "+ tasks.size()+(tasks.size()!=1?" tasks":" task")+" in the list :D");
    }
}
