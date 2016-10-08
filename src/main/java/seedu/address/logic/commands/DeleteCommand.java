package seedu.address.logic.commands;

import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.UnmodifiableObservableList;
import seedu.address.model.task.ReadOnlyTask;
import seedu.address.model.task.UniqueTaskList.PersonNotFoundException;

/**
 * Deletes a person identified using it's last displayed index from the address
 * book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number used in the last task listing.\n"
            + "Parameters: either INDEX (must be a positive integer) or TASKNAME\n" + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Task: %1$s";
    public static final String MESSAGE_DELETE_TASK_FAILURE = "No such task was found.";
    public static final String MESSAGE_DELETE_IN_NEXT_STEP = "Multiple tasks were found containing the entered keywords. Please check below and delete by index.";

    public final boolean deleteByIndex;

    public int targetIndex;
    public String taskName;

    public DeleteCommand(int targetIndex) {
    	deleteByIndex = true;
        this.targetIndex = targetIndex-1;
    }

    public DeleteCommand(String taskName) {
    	deleteByIndex = false;
        taskName = taskName.trim();
        this.taskName = taskName;
    }
    
    @Override
    public CommandResult execute(){
    	if(deleteByIndex){
    		return deleteUsingIndex();
    	}
    	else{
    		return deleteUsingString();
    	}	
    }
    
    private CommandResult deleteUsingString(){
    	Set<String> taskNameSet = new HashSet<String>();
    	taskNameSet.add(taskName);
    	model.updateFilteredTaskList(taskNameSet);
    	UnmodifiableObservableList<ReadOnlyTask> matchingTasks = model.getFilteredPersonList();
    	
    	// No tasks match string
    	if (matchingTasks.isEmpty()){
    		model.updateFilteredListToShowAll();
    		return new CommandResult(String.format(MESSAGE_DELETE_TASK_FAILURE));
    	}
    	
    	// Only 1 task matches string
    	else if (matchingTasks.size() == 1) {
    		ReadOnlyTask taskToDelete = matchingTasks.get(0);
    		try {
				model.deleteTask(taskToDelete);
			}
    		catch (PersonNotFoundException e) {
				assert false: "The target task cannot be missing";
			}
    		model.updateFilteredListToShowAll();
    		return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete.getName()));
    	} 
    	
    	//More than 1 task matches string
    	else {
    		Set<String> keywords = new HashSet<String>();
    		keywords.add(taskName);
    		model.updateFilteredTaskList(keywords);
    		return new CommandResult(String.format(MESSAGE_DELETE_IN_NEXT_STEP));
    	}
    }
    
    
    private CommandResult deleteUsingIndex(){
    	UnmodifiableObservableList<ReadOnlyTask> lastShownList = model.getFilteredPersonList();
    	if(targetIndex >= lastShownList.size()){
    		return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    	}
    	else{
    		ReadOnlyTask taskToDelete = lastShownList.get(targetIndex);
    		try{
    			model.deleteTask(taskToDelete);
    		}
    		catch (PersonNotFoundException e){
    			assert false: "The target task cannot be missing";
    		}
    		return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete.getName()));
    	}
    }
}