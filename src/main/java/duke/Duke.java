package duke;

import duke.exception.DukeException;
import duke.exception.DukeOutOfBoundsException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

public class Duke {
    private static Storage storage;
    private static Ui ui;
    public static TaskList taskList;
    public enum TaskType{
        TODO , DEADLINE, EVENT, INVALID
    }

    //public static Storage file = new Storage("duke.txt", "data" );

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

    public void run(){
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

    public static void main(String[] args) {
       new Duke("duke.txt", "data").run();
    }

    public static void updateFileTasks() {
        try {
            storage.updateFile(taskList.tasks);
        }catch(IOException e){
            ui.printFileError(e);
        }
    }
}
