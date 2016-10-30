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
	public static final String MESSAGE_NOT_CHRONO_TASK = "The start time must be before the end time.";
	public static final String MESSAGE_OVERLAP = "There is an overlap with other existing task(s).";

	private final Task toAdd;

	/**
	 * Convenience constructor using raw values.
	 *
	 * @throws IllegalValueException if any of the raw values are invalid
	 */
	public AddCommand(String name, String startTime, String endTime, String priority, Set<String> tags, String frequency) throws IllegalValueException {
		final Set<Tag> tagSet = new HashSet<>();
		for (String tagName : tags) {
			tagSet.add(new Tag(tagName));
		}
		this.toAdd = new Task(
				new TaskDetails(name.replace("\\", "")),
				new StartTime(startTime),
				new EndTime(endTime),
				new Priority(priority),
				new UniqueTagList(tagSet),
				frequency
				);
	}

	public AddCommand(String name, String startTime, String endTime, String priority, Set<String> tags) throws IllegalValueException {
		this(name, startTime, endTime, priority, tags, "");
	}

	/**
	 * Constructor for floating tasks.
	 * 
	 * @throws IllegalValueException if any of the raw values are invalid
	 */
	public AddCommand(String name, String priority, Set<String> tags) throws IllegalValueException {
		this(name, "", "", priority ,tags, "");
	}

	@Override
	public CommandResult execute() {
		assert model != null;
		try {
			isNotChronoTime(toAdd.getStartTime(), toAdd.getEndTime());
			if (model.isDuplicate(toAdd)){
			    return new CommandResult(MESSAGE_DUPLICATE_TASK);
			}
			model.addTask(toAdd);
			if (model.isOverlapping(toAdd)) {
				model.updateFilteredListToShowOverlapping(toAdd);
				return new CommandResult(String.format(MESSAGE_SUCCESS + ". " + MESSAGE_OVERLAP, toAdd.getTaskDetails()));
			}
			else {
				return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.getTaskDetails()));
			}
		} 
		catch(IllegalValueException ive){
			return new CommandResult(ive.getMessage());
		}

	}
	public boolean isNotChronoTime(StartTime starttime, EndTime endtime) throws IllegalValueException{
		if(endtime.time.getTimeInMillis()==0){
			return false;
		}
		boolean finalres;
		finalres = starttime.getAsCalendar().after(endtime.getAsCalendar());
		if(finalres){
			throw new IllegalValueException(MESSAGE_NOT_CHRONO_TASK);
		}
		else{
			return false;
		}
	}

}
