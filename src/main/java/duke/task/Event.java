package duke.task;

/**
 * Represents a kind of task which is an Event 'at' when it occurs.
 */
public class Event extends Task {
    public String at;

    /**
     * Parameterised constructor to create an object with description and at when the tasks must be completed.
     * @param description String type describing the task.
     * @param at String type specifying time/date at when the task occurs.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * A getter function used to get instance variable 'at.'
     * @return String type instance variable at.
     */
    public String getAt(){
        return at;
    }

    /**
     * Specifies how the Event object type must be represented by a String.
     * @return String type representation of the Event object.
     */
    @Override
    public String toString(){
        return "[E]["+getStatusIcon()+"] "+description+"(at:"+at+")";
    }
}
