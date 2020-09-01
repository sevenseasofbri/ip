import java.util.Scanner;
public class Duke {
    public static Task[] list = new Task[100];
    public static int numItems;
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static final String LINE_BREAK = "\t____________________________________________________________";
    public static final String YOUR_LIST = "\t_______________________~Your List~__________________________";
    public enum TaskType{
        TODO, DEADLINE, EVENT
    }

    public static void printList(){
        System.out.println(YOUR_LIST);
        for(int i=0; i<numItems; i++){
            System.out.println("\t"+(i+1)+". "+ list[i]);
        }
        System.out.println(LINE_BREAK);
    }
    public static void addToList(String answer, TaskType taskType){
        numItems++;
        String taskDescription, time;
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
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalTasksDone=0;
        String answer="";
        System.out.println(LINE_BREAK);
        System.out.println("\tHello! I'm\n"+ LOGO);
        System.out.println("\tWhat can I do for you?");
        System.out.println(LINE_BREAK);
        while(true){
            answer = in.nextLine();
            if(answer.trim().equalsIgnoreCase("bye")){
                break;
            }
            if(answer.trim().equalsIgnoreCase("list")){
                printList();
                continue;
            }
            if(answer.contains("done")){
                answer = answer.trim();
                String[] words = answer.split(" ");
                if(words.length>1) {
                    int valueToMarkDone = Integer.parseInt(words[1]);
                    if(valueToMarkDone>0 && valueToMarkDone<=numItems) {
                        if(!list[valueToMarkDone-1].isDone){
                            totalTasksDone++;
                        }
                        list[valueToMarkDone - 1].markAsDone();
                        System.out.println("\tAwesome! I've marked this task as done:");
                        System.out.println("\t["+list[valueToMarkDone - 1]);
                        System.out.println("\tOnly "+(numItems-totalTasksDone)+" to go! ;)");
                    }
                    else{
                        System.out.println("\tSorry could not mark as done! Please enter a valid list number.");
                    }
                }
            }
            else {
               String task = answer.substring(answer.trim().indexOf(" "));
               TaskType taskType = getTaskType(answer);
               addToList(task, taskType);
            }
        }
        System.out.println(LINE_BREAK);
        System.out.println("\tFarewell. Until next time my dude.");
        System.out.println(LINE_BREAK);
    }

    private static TaskType getTaskType(String answer) {
        TaskType taskType;
        if(answer.contains("deadline")){
            taskType = TaskType.DEADLINE;
        }
        else if(answer.contains("event")){
            taskType = TaskType.EVENT;
        }
        else{
            taskType = TaskType.TODO;
        }
        return taskType;
    }
}
