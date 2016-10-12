package seedu.tasklist.logic.commands;

import java.util.HashSet;
import java.util.Set;

import seedu.tasklist.commons.exceptions.IllegalValueException;
import seedu.tasklist.model.tag.Tag;
import seedu.tasklist.model.tag.UniqueTagList;
import seedu.tasklist.model.task.*;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Adds a task to the to-do list."
            + "Parameters: NAME s/START e/END p/PRIORITY [t/TAG]...\n\t"
            + "Example: " + COMMAND_WORD
            + " Sleep s/8 Oct e/9 Oct p/high";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the to-do list.";

    private final Task toAdd;

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public AddCommand(String name, String startTime, String endTime, String priority, Set<String> tags) throws IllegalValueException {
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        String[] startTimeDate = new String[2];
    	String[] endTimeDate = new String[2];
    	startTimeDate = startTime.split(" ");
    	endTimeDate = endTime.split(" ");   
    	String finalStartTime = "";
    	String finalStartDate = "";
    	String finalEndTime= "";
    	String finalEndDate= "";
    	
    	if(startTimeDate[0].length()<startTimeDate[1].length()){
    	  finalStartTime = startTimeDate[0];
    	  finalStartDate = startTimeDate[1];
    	}
    	else if(startTimeDate[0].length()>startTimeDate[1].length()){
    		finalStartDate = startTimeDate[0];
    		finalStartTime = startTimeDate[1];
    	}
    	if(endTimeDate[0].length()<endTimeDate[1].length()){
        	 finalEndTime = endTimeDate[0];
        	 finalEndDate = endTimeDate[1];
        	}
        	else if(endTimeDate[0].length()>endTimeDate[1].length()){
               finalEndDate = endTimeDate[0];
               finalEndTime = endTimeDate[1];
        	}
    
           this.toAdd = new Task(
                new TaskDetails(name),
                new StartTime(finalStartTime),
                new StartDate(finalStartDate),
                new EndTime(finalEndTime),
                new EndDate(finalEndDate),
                new Priority(priority),
                new UniqueTagList(tagSet)
        );
    }
    
    /**
     * Constructor for floating tasks.
     * 
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public AddCommand(String name, String priority, Set<String> tags) throws IllegalValueException {
    	final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
    	this.toAdd = new Task(
				new TaskDetails(name),
				new Priority(priority),
                new UniqueTagList(tagSet));
    }

    @Override
    public CommandResult execute() {
        assert model != null;
        try {
            model.addTask(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (UniqueTaskList.DuplicatePersonException e) {
            return new CommandResult(MESSAGE_DUPLICATE_TASK);
        }

    }

}
