import java.util.Scanner;
public class Duke {
    public static Task[] list = new Task[100];
    public static int numItems;
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
        printMessage("\tHello! I'm\n"+ LOGO+"\n\tWhat can I do for you?");
        while(isNotDone){
            answer = in.nextLine();
            try {
                isNotDone = parseCommand(answer);
            }catch (DukeException e){
                printMessage("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }catch (ArrayIndexOutOfBoundsException e){
                printMessage("\tEmpty command! Please specify the task number to mark as complete.");
            }catch (NumberFormatException e){
                printMessage("\tSorry could not mark as done! Please enter a valid number.");
            }catch (DukeOutOfBoundsException e){
                printMessage("\tCannot mark task as done! :(\n\tPlease enter a list number within the range.");
            }
        }
        printMessage("\tFarewell. Until next time my dude.");
//        System.out.println(LINE_BREAK);
//        System.out.println("\tFarewell. Until next time my dude.");
//        System.out.println(LINE_BREAK);
    }

//    private static void printWelcomeMessage() {
//        System.out.println(LINE_BREAK);
//        System.out.println("\tHello! I'm\n"+ LOGO);
//        System.out.println("\tWhat can I do for you?");
//        System.out.println(LINE_BREAK);
//    }
    public static void printList(){
        System.out.println(YOUR_LIST);
        for(int i=0; i<numItems; i++){
            System.out.println("\t"+(i+1)+". "+ list[i]);
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
        if(answer.contains("deadline")){
            taskType = TaskType.DEADLINE;
        } else if(answer.contains("event")){
            taskType = TaskType.EVENT;
        } else if(answer.contains("todo")){
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
            String[] words = answer.trim().split(" ");
            int valueToMarkDone = Integer.parseInt(words[1]);
            if(valueToMarkDone<=0 || valueToMarkDone>numItems) {
                throw new DukeOutOfBoundsException();
            }
                if (!list[valueToMarkDone - 1].isDone) {
                        totalTasksDone++;
                }
                list[valueToMarkDone - 1].markAsDone();
                System.out.println("\tAwesome! I've marked this task as done:");
                System.out.println("\t[" + list[valueToMarkDone - 1]);
                System.out.println("\tOnly " + (numItems - totalTasksDone) + " to go! ;)");
                return true;
        }
        else {
            TaskType taskType = getTaskType(answer);
            if(taskType == TaskType.INVALID){
                throw new DukeException();
            }
            try {
                addToList(answer, taskType);
            }catch(EmptyTaskException e){
                printMessage("☹ OOPS!!! The description of a"+
                        (taskType==TaskType.TODO?"todo":(taskType==TaskType.DEADLINE?"deadline":"event"))+
                        "cannot be empty.");
            }
            return true;
        }
    }

    public static void addToList(String answer, TaskType taskType) throws EmptyTaskException{
        String[] words = answer.trim().split(" ");
        //Checks if task description is empty
        if(words.length<2){
            throw new EmptyTaskException();
        }
        answer = answer.substring(answer.indexOf(" "));
        String taskDescription, time;
        numItems++;
        switch (taskType){
        case TODO:
            list[numItems-1] = new ToDo(answer);
            break;
        case DEADLINE:
            taskDescription = answer.substring(0, answer.indexOf("/by"));
            time = answer.substring(answer.indexOf("/by ")+3);
            list[numItems-1] = new Deadline(taskDescription,time);
            break;
        case EVENT:
            taskDescription = answer.substring(0, answer.indexOf("/at"));
            time = answer.substring(answer.indexOf("/at ")+3);
            list[numItems-1] = new Event(taskDescription, time);
        }
        System.out.println(LINE_BREAK);
        System.out.println("\tAdded:" + list[numItems-1]);
        System.out.println("\tNow you have "+numItems+(numItems>1?" tasks":" task")+" in the list :D");
        System.out.println(LINE_BREAK);
    }
}
