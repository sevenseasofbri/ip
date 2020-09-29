package duke.task;

/**
 * Represents a kind of task which is a task to be done or a 'todo'.
 */
public class ToDo extends Task{
    /**
     * Parameterised constructor to create an object with the description specified.
     *
     * @param description
     */
    public ToDo(String description){
        super(description);
    }

    /**
     * Specifies how the ToDo object type must be represented by a String.
     *
     * @return String type representation of the ToDo object.
     */
    @Override
    public String toString(){
        return "[T]["+getStatusIcon()+"] "+description;
    }
}

