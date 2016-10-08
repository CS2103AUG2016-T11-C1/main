package seedu.address.logic.commands;

import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.UnmodifiableObservableList;
import seedu.address.model.task.ReadOnlyTask;
import seedu.address.model.task.UniqueTaskList.PersonNotFoundException;


public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks a task as complete\n"
            + "Parameters: either INDEX (must be a positive integer) or TASKNAME\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DONE_TASK_SUCCESS = "Completed Task: %1$s";
    public static final String MESSAGE_DONE_TASK_FAILURE = "No such task was found.";
    public static final String MESSAGE_DONE_IN_NEXT_STEP = "Multiple tasks were found containing the entered keywords. Please check below and mark as complete by index.";

    public final boolean doneByIndex;

    public int targetIndex;
    public String taskName;
    
    public DoneCommand(int targetIndex) {
        doneByIndex = true;
        this.targetIndex = targetIndex-1;
    }

    public DoneCommand(String taskName) {
        doneByIndex = false;
        taskName = taskName.trim();
        this.taskName = taskName;
    }

    @Override
    public CommandResult execute() {
        if(doneByIndex){
            return doneUsingIndex();
        }
        else{
            return doneUsingString();
        }   
    }
    
    private CommandResult doneUsingIndex(){
        UnmodifiableObservableList<ReadOnlyTask> lastShownList = model.getFilteredPersonList();
        if(targetIndex >= lastShownList.size()){
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        else{
            ReadOnlyTask taskToMark = lastShownList.get(targetIndex);
            try{
                model.markTaskAsComplete(taskToMark);
            }
            catch (PersonNotFoundException e){
                assert false: "The target task cannot be missing";
            }
            return new CommandResult(String.format(MESSAGE_DONE_TASK_SUCCESS, taskToMark.getName()));
        }
    }

    private CommandResult doneUsingString(){
    	Set<String> taskNameSet = new HashSet<String>();
    	taskNameSet.add(taskName);
    	model.updateFilteredTaskList(taskNameSet);
    	UnmodifiableObservableList<ReadOnlyTask> matchingTasks = model.getFilteredPersonList();
    	
    	// No tasks match string
    	if (matchingTasks.isEmpty()){
    		model.updateFilteredListToShowAll();
    		return new CommandResult(String.format(MESSAGE_DONE_TASK_FAILURE));
    	}
    	
    	// Only 1 task matches string
    	else if (matchingTasks.size() == 1) {
    		ReadOnlyTask taskToMark = matchingTasks.get(0);
    		try {
				model.deleteTask(taskToMark);
			}
    		catch (PersonNotFoundException e) {
				assert false: "The target task cannot be missing";
			}
    		model.updateFilteredListToShowAll();
    		return new CommandResult(String.format(MESSAGE_DONE_TASK_SUCCESS, taskToMark.getName()));
    	} 
    	
    	//More than 1 task matches string
    	else {
    		Set<String> keywords = new HashSet<String>();
    		keywords.add(taskName);
    		model.updateFilteredTaskList(keywords);
    		return new CommandResult(String.format(MESSAGE_DONE_IN_NEXT_STEP));
    	}
    }
    
    boolean targetIndexWasEntered() {
        return (targetIndex >= 0);
    }

}