import java.util.*;
public class Duke {
    public static void printList(Task[] items, int numItems){
        System.out.println("\t_______________________~Your List~__________________________");
        for(int i=0; i<numItems; i++){
            System.out.println("\t"+(i+1)+". ["+items[i].getStatusIcon()+"] "+items[i].description);
        }
        System.out.println("\t____________________________________________________________");
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Task[] list = new Task[100];
        int numItems =0, totalTasksDone=0;
        String answer="";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm\n"+ logo);
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
        while(true){
            answer = in.nextLine();
            if(answer.trim().equalsIgnoreCase("bye")){
                break;
            }
            if(answer.trim().equalsIgnoreCase("list")){
                printList(list, numItems);
                continue;
            }
            if(answer.indexOf("done") > -1){
                answer = answer.trim();
                String[] words = answer.split(" ");
                if(words.length>1) {
                    int valueMarkedDone = Integer.parseInt(words[1]);
                    if(valueMarkedDone>0 && valueMarkedDone<=numItems) {
                        list[valueMarkedDone - 1].markAsDone();
                        totalTasksDone++;
                        System.out.println("\tAwesome! I've marked this task as done:");
                        System.out.println("\t["+list[valueMarkedDone - 1].getStatusIcon()+"] "+list[valueMarkedDone - 1].description);
                        System.out.println("\tOnly "+(numItems-totalTasksDone)+" to go! ;)");
                    }
                    else{
                        System.out.println("\tSorry could not mark as done! Please enter a valid list number.");
                    }
                }
            }
            else {
                Task newTask = new Task(answer);
                list[numItems] = newTask;
                numItems++;
                System.out.println("\tAdded:" + answer);
            }
        }
        System.out.println("\t____________________________________________________________");
        System.out.println("\tFarewell. Until next time my dude.");
        System.out.println("\t____________________________________________________________");
    }
}
