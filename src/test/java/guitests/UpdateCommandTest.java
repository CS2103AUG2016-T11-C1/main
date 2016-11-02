//@@author A0144919W
package guitests;

import org.junit.Test;

import seedu.tasklist.commons.core.Messages;
import seedu.tasklist.commons.exceptions.IllegalValueException;
import seedu.tasklist.logic.commands.UpdateCommand;
import seedu.tasklist.model.task.EndTime;
import seedu.tasklist.model.task.Priority;
import seedu.tasklist.model.task.StartTime;
import seedu.tasklist.model.task.TaskDetails;
import seedu.tasklist.testutil.TestTask;
import seedu.tasklist.testutil.TypicalTestTasks;

public class UpdateCommandTest extends TaskListGuiTest {
    
    @Test
    public void update() throws IllegalValueException {
        TestTask[] currentList = td.getTypicalTasks();

        //updating a non-existing task
        commandBox.runCommand("update 20 Buy eggs");
        assertResultMessage(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);

        //updating all information for a task
        commandBox.runCommand("update 1 Attend yoga session from today 2pm to today 4pm p/high");
        TypicalTestTasks.task1.setTaskDetails(new TaskDetails("Attend yoga session"));
        TypicalTestTasks.task1.getStartTime().updateTime("2pm");
        TypicalTestTasks.task1.getEndTime().updateTime("4pm");
        TypicalTestTasks.task1.setPriority(new Priority("high"));      
        assertResultMessage(String.format(UpdateCommand.MESSAGE_UPDATE_TASK_SUCCESS, currentList[0].getTaskDetails()));
        
        //updating task description for a task
        commandBox.runCommand("update 1 Attend yoga session");
        TypicalTestTasks.task2.setTaskDetails(new TaskDetails("Attend yoga session"));
        assertResultMessage(String.format(UpdateCommand.MESSAGE_UPDATE_TASK_SUCCESS, currentList[1].getTaskDetails()));

        //updating start time for a task
        commandBox.runCommand("update 7 at 10am");
        TypicalTestTasks.task7.getStartTime().updateTime("10am");
        assertResultMessage(String.format(UpdateCommand.MESSAGE_UPDATE_TASK_SUCCESS, currentList[6].getTaskDetails()));
        
        //updating end time for a task
        commandBox.runCommand("update 5 by 1pm");
        TypicalTestTasks.task5.getEndTime().updateTime("1pm");
        assertResultMessage(String.format(UpdateCommand.MESSAGE_NOT_CHRONO_TASK, currentList[4].getTaskDetails()));
        
        //updating a floating task to a task with date and time
        commandBox.runCommand("update 3 at 5pm");
        TypicalTestTasks.task3.getStartTime().updateTime("5pm");
        assertResultMessage(String.format(UpdateCommand.MESSAGE_UPDATE_TASK_SUCCESS, currentList[2].getTaskDetails()));
        
    }
  
}