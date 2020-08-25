import java.util.*;
public class Duke {
    public static void printList(String[] items){
        System.out.println("\t_______________________~Your List~__________________________");
        for(int i=0; i<items.length; i++){
            System.out.println("\t"+(i+1)+". "+items[i]);
        }
        System.out.println("\t____________________________________________________________");
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] list = new String[100];
        int numItems =0;
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
                printList(Arrays.copyOf(list, numItems));
            }
            else {
                list[numItems] = answer;
                numItems++;
                System.out.println("\tAdded:" + answer);
            }
        }
        System.out.println("\t____________________________________________________________");
        System.out.println("\tFarewell. Until next time my dude.");
        System.out.println("\t____________________________________________________________");
    }
}
