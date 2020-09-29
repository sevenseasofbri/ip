package duke.task;

/**
 * Represents a task which has a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Parameterised constructor to accept and specify task description and set initial completion status.
     *
     * @param description String type describing the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a tick or cross status icon based on completion status of the task.
     *
     * @return String type tick or cross icon. If task is complete then tick else a cross.
     */
    public String getStatusIcon() {

        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone(){
        this.isDone=true;
    }

    /**
     * A getter function that returns completion status of the task.
     *
     * @return A boolean instance variable isDone.
     */
    public boolean getDoneStatus(){
        return isDone;
    }

    /**
     * A getter function that returns description of the task.
     *
     * @return String type instance variable description.
     */
    public String getDescription(){
        return description;
    }

}
