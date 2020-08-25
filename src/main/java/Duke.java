import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
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
            System.out.println("\t"+answer);
        }
        System.out.println("\tFarewell. Until next time my dude.");
        System.out.println("\t____________________________________________________________");
    }
}
