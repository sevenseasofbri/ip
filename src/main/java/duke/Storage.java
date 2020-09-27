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

public class Storage {
    protected String fileName;
    protected String directory;
    protected File file;

    public Storage(String name, String dir){
        fileName = name;
        directory = dir;
        createFile();
    }

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

    public String getTaskType(Task task){
        if(task instanceof ToDo){
            return "T";
        }else if(task instanceof Deadline){
            return "D";
        }else{
            return "E";
        }
    }

    public String getDoneStatus(Task task){
        if(task.getDoneStatus()){
            return "1";
        }else{
            return "0";
        }
    }

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
