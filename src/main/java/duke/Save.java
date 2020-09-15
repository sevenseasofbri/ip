package duke;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Save {
    protected String fileName;
    protected String directory;
    protected File file;

    public Save(String name, String dir){
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

    public void updateFile(Task[] tasks, int numItems) throws IOException{ //ArrayList<Task> tasks
        FileWriter fw = new FileWriter(file);
        String taskType;
        String done;
        String textIn="";
        for(int i=0; i< numItems; i++){
            taskType = getTaskType(tasks[i]);
            done = getDoneStatus(tasks[i]);
            switch (taskType){
            case "T":
                textIn = textIn+ (taskType+" | "+done+" | "+tasks[i].description+System.lineSeparator());
                break;
            case "D":
                textIn = textIn + (taskType+" | "+done+" | "+tasks[i].description+" | "+((Deadline) tasks[i]).getBy()+System.lineSeparator());
                break;
            case "E":
                textIn = textIn + (taskType+" | "+done+" | "+tasks[i].description+" | "+((Event) tasks[i]).getAt()+System.lineSeparator());
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
        if(task.isDone){
            return "1";
        }else{
            return "0";
        }
    }

    public void readFile(ArrayList<Task> tasks) throws FileNotFoundException {
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
    }
}
