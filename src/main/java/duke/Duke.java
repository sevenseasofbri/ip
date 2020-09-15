package duke;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    //public static Task[] list = new Task[100];
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static int totalTasksDone=0;
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static final String LINE_BREAK = "\t____________________________________________________________";
    public static final String YOUR_LIST = "\t_______________________~Your List~__________________________";
    public enum TaskType{
        TODO , DEADLINE, EVENT, INVALID
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean isNotDone=true;
        String answer;
        //printWelcomeMessage();
        printMessage("\tHello! I'm\n"+ LOGO+"\n\tWhat can I do for you?\uD83D\uDE0A");
        while(isNotDone){
            answer = in.nextLine();
            try {
                isNotDone = parseCommand(answer);
            }catch (DukeException e){
                printMessage("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }catch (ArrayIndexOutOfBoundsException e){
                printMessage("\t☹ Empty command! Please specify the task number to mark as complete.");
            }catch (NumberFormatException e){
                printMessage("\t☹ Sorry could not mark as done! Please enter a valid number.");
            }catch (DukeOutOfBoundsException e){
                printMessage("\t☹ Cannot mark task as done! :(\n\tPlease enter a list number within the range.");
            }
        }
        printMessage("\tFarewell. Until next time my dude.");
    }

    public static void printList(){
        System.out.println(YOUR_LIST);
        int index =0;
        for(Task task: tasks){
            System.out.println("\t"+(index+1)+". "+ task);
            index++;
        }
        System.out.println(LINE_BREAK);
    }

    private static void printMessage(String message){
        System.out.println(LINE_BREAK);
        System.out.println(message);
        System.out.println(LINE_BREAK);
    }
    private static TaskType getTaskType(String answer) {
        TaskType taskType;
        answer = answer.trim();
        if(answer.matches("deadline(.*)")){
            taskType = TaskType.DEADLINE;
        } else if(answer.matches("event(.*)")){
            taskType = TaskType.EVENT;
        } else if(answer.matches("todo(.*)")){
            taskType = TaskType.TODO;
        } else{
            taskType = TaskType.INVALID;
        }
        return taskType;
    }

    private static boolean parseCommand(String answer) throws DukeException, DukeOutOfBoundsException{
        if(answer.trim().equalsIgnoreCase("bye")){
            return false;
        }
        if(answer.trim().equalsIgnoreCase("list")){
            printList();
            return true;
        }
        if(answer.contains("done")){
            markTaskAsDone(answer);
        } else {
            TaskType taskType = getTaskType(answer);
            if(taskType == TaskType.INVALID){
                throw new DukeException();
            }
            try {
                addToList(answer, taskType);
            }catch(EmptyTaskException e){
                printMessage("\t☹ OOPS!!! The description of a "+
                        (taskType==TaskType.TODO?"todo":(taskType==TaskType.DEADLINE?"deadline":"event"))+
                        " cannot be empty.");
            }catch(StringIndexOutOfBoundsException e){
                printMessage("\t☹ Please start with the command with todo/deadline/event!"+
                        "\n\t OR please specify a date/time if you want to set a deadline/event!");
            }catch (InvalidFormatException e){
                printMessage("\t☹ Remember to format your command like this ⬇" +
                        "\n\t todo <description>"+
                        "\n\t deadline <description> /by <date/time>"+
                        "\n\t event <description> /at <date/time>");
            }
        }
        return true;
    }

    private static void markTaskAsDone(String answer) throws DukeOutOfBoundsException {
        String[] words = answer.trim().split(" ");
        int valueToMarkDone = Integer.parseInt(words[1]);
        if(valueToMarkDone<=0 || valueToMarkDone>tasks.size()) {
            throw new DukeOutOfBoundsException();
        }
        if (!tasks.get(valueToMarkDone-1).isDone) {
                totalTasksDone++;
        }
        tasks.get(valueToMarkDone-1).markAsDone();
        printMessage("\tAwesome! I've marked this task as done:"+"\n\t" +
                 tasks.get(valueToMarkDone-1)+
                "\n\tOnly " + (tasks.size() - totalTasksDone) + " to go! ;)");
    }

    public static void addToList(String answer, TaskType taskType) throws EmptyTaskException, InvalidFormatException{
        String[] words = answer.trim().split(" ");
        //Checks if task description is empty
        if(words.length<2){
            throw new EmptyTaskException();
        }
        answer = answer.substring(answer.indexOf(" "));
        String taskDescription, time;
        switch (taskType){
        case TODO:
            tasks.add(new ToDo(answer));
            break;
        case DEADLINE:
            if(!answer.contains(" /by ")){
                throw new InvalidFormatException();
            }
            taskDescription = answer.substring(0, answer.indexOf("/by"));
            time = answer.substring(answer.indexOf("/by ")+3);
            tasks.add(new Deadline(taskDescription, time));
            break;
        case EVENT:
            if(!answer.contains(" /at ")){
                throw new InvalidFormatException();
            }
            taskDescription = answer.substring(0, answer.indexOf("/at"));
            time = answer.substring(answer.indexOf("/at ")+3);
            tasks.add(new Event(taskDescription, time));
        }
        printMessage("\tAdded:" + tasks.get(tasks.size()-1) +
                "\n\tNow you have "+tasks.size()+(tasks.size()>1?" tasks":" task")+" in the list :D");
    }
}
