package duke;

public class Event extends Task{
    public String at;

    public Event(String description, String at){
        super(description);
        this.at = at;
    }

    public String getAt(){
        return at;
    }

    @Override
    public String toString(){
        return "[E]["+getStatusIcon()+"] "+description+"(at:"+at+")";
    }
}
