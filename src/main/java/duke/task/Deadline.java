package duke.task;

/**
 * Represents a kind of task which has a Deadline 'by' which the task must be completed.
 */
public class Deadline extends Task {
    public String by;

    /**
     * Parameterised constructor to create an object with description and by when the tasks must be completed.
     *
     * @param description String type describing the task.
     * @param by String type specifying time/date by when the task must be complete.
     */
    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    /**
     * A getter function used to get instance variable 'by.'
     *
     * @return String type instance variable by.
     */
    public String getBy(){
        return by;
    }

    /**
     * Specifies how the Deadline object type must be represented by a String.
     *
     * @return String type representation of the Deadline object.
     */
    @Override
    public String toString(){
        return "[D]["+getStatusIcon()+"] "+description+"(by:"+by+")";
    }

}
