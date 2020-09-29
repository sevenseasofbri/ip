package duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Each instance of storage represents the ability to create, read and write Task lists
 * to a certain file as specified.
 */
public class Storage {
    protected String fileName;
    protected String directory;
    protected File file;

    /**
     * Parameterised constructor that takes in the filename and directory to create a new file.
     * @param name The name and extension type of the file where the task data will be stored.
     * @param dir The name of the folder in which the file with task data will be stored.
     */
    public Storage(String name, String dir){
        fileName = name;
        directory = dir;
        createFile();
    }

    /**
     * Creates a file by making a new file object with the directory and file name specified when a new
     * object of the class is made. Checks for existence of the file and folder before making them. Catches
     * IOException in case something goes wrong while making the file and directory.
     */
    public void createFile(){
        file = new File(directory+"/"+fileName);
        try{
            if(!file.exists()){
                if(!file.getParentFile().exists()){
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
        }catch(IOException e){
            System.out.println("\tSomething went wrong: "+ e.getMessage());
        }
    }

    /**
     * Updates the existing file created with the task list specified in the parameter. Writes to the file in
     * a specific format.
     * @param tasks An ArrayList<Task> which specifies the current tasks to be written to the file.
     * @throws IOException If the output operation to the file has failed or was interrupted.
     */
    public void updateFile(ArrayList<Task> tasks) throws IOException{
        FileWriter fw = new FileWriter(file);
        String taskType;
        String done;
        String textIn="";
        for(Task task: tasks){
            taskType = getTaskType(task);
            done = getDoneStatus(task);
            switch (taskType){
            case "T":
                textIn = textIn+ (taskType+" | "+done+" | "+task.getDescription()+System.lineSeparator());
                break;
            case "D":
                textIn = textIn + (taskType+" | "+done+" | "+task.getDescription()+" | "+((Deadline) task).getBy()+System.lineSeparator());
                break;
            case "E":
                textIn = textIn + (taskType+" | "+done+" | "+task.getDescription()+" | "+((Event) task).getAt()+System.lineSeparator());
            }
        }
        fw.write(textIn);
        fw.close();
    }

    /**
     * Returns a string "T", "D" or "E" based on the type of task passed to the function.
     * @param task A Task class object which could be an object of subclass Deadline, Event or ToDo.
     * @return A String "T", "D" or "E" if the task is and instance of ToDo, Deadline or Event respectively.
     */
    public String getTaskType(Task task){
        if(task instanceof ToDo){
            return "T";
        }else if(task instanceof Deadline){
            return "D";
        }else{
            return "E";
        }
    }

    /**
     * Returns a String "0" or "1" based on whether the task passed is marked as done or not.
     * @param task An instance of the Task class.
     * @return A String "0" or "1" if the task is marked as not done or done respectively.
     */
    public String getDoneStatus(Task task){
        if(task.getDoneStatus()){
            return "1";
        }else{
            return "0";
        }
    }

    /**
     * Returns an ArrayList<Task> of tasks after reading values from the current file. The data is read on
     * assumption that the format of data is same as the one specified by function updateFile().
     * @return An ArrayList<Task> with the tasks specified in the file.
     * @throws FileNotFoundException If the file does not exist or cannot be found.
     */
    public ArrayList<Task> readFile() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(file);
        int numTask = 0;
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] details = line.split("\\|");
            switch (details[0].trim()) {
            case "T":
                tasks.add(new ToDo(details[2].trim()));
                break;
            case "D":
                tasks.add(new Deadline(details[2].trim(), details[3].trim()));
                break;
            case "E":
                tasks.add(new Event(details[2].trim(), details[3].trim()));
            }
            if(details[1].trim().equals("1")) {
                tasks.get(numTask).markAsDone();
            }
            numTask++;
        }
        return tasks;
    }
}
