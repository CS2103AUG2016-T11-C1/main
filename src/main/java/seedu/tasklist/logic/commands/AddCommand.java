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
            model.addTask(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (UniqueTaskList.DuplicateTaskException e) {
            return new CommandResult(MESSAGE_DUPLICATE_TASK);
        }

    }

}
