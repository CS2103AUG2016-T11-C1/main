//@@author A0144919W
package guitests;

import org.junit.Test;

import seedu.tasklist.commons.exceptions.IllegalValueException;
import seedu.tasklist.logic.commands.ClearCommand;
import seedu.tasklist.logic.commands.DoneCommand;
import seedu.tasklist.logic.commands.UndoCommand;
import seedu.tasklist.logic.commands.UpdateCommand;
import seedu.tasklist.model.task.EndTime;
import seedu.tasklist.model.task.Priority;
import seedu.tasklist.model.task.StartTime;
import seedu.tasklist.model.task.TaskDetails;
import seedu.tasklist.testutil.TypicalTestTasks;

public class UndoCommandTest extends TaskListGuiTest {

    @Test
    public void undoOneChange() throws IllegalValueException {
        //undo a change that was never made
        commandBox.runCommand("update 20 Buy eggs");
        commandBox.runCommand("undo");
        assertResultMessage(UndoCommand.MESSAGE_SUCCESS);
        //undo one change
        commandBox.runCommand("update 1 Attend yoga session from 2pm to 4pm p/high");
        TypicalTestTasks.task1.setTaskDetails(new TaskDetails("Attend yoga session"));
        TypicalTestTasks.task1.setStartTime(new StartTime("2pm"));
        TypicalTestTasks.task1.setEndTime(new EndTime("4pm"));
        TypicalTestTasks.task1.setPriority(new Priority("high")); 
        commandBox.runCommand("undo");
        TypicalTestTasks.task1.setTaskDetails(new TaskDetails("Buy eggs"));
        TypicalTestTasks.task1.setStartTime(new StartTime("5pm"));
        TypicalTestTasks.task1.setEndTime(new EndTime(""));
        TypicalTestTasks.task1.setPriority(new Priority("high"));
        assertResultMessage(UndoCommand.MESSAGE_SUCCESS);
    }
    
    @Test
    public void undoTwoChanges() throws IllegalValueException {
        //undo a change that was never made
        commandBox.runCommand("update 2 Buy eggs");
        commandBox.runCommand("undo");
        assertResultMessage(UndoCommand.MESSAGE_SUCCESS);
        //undo two changes
        commandBox.runCommand("update 1 Attend yoga session from 2pm to 4pm p/high");
        TypicalTestTasks.task1.setTaskDetails(new TaskDetails("Attend yoga session"));
        TypicalTestTasks.task1.setStartTime(new StartTime("2pm"));
        TypicalTestTasks.task1.setEndTime(new EndTime("4pm"));
        TypicalTestTasks.task1.setPriority(new Priority("high")); 
        commandBox.runCommand("delete 2");
        commandBox.runCommand("undo");
        assertResultMessage(UndoCommand.MESSAGE_SUCCESS);
        commandBox.runCommand("undo");
        commandBox.runCommand("update 1 Buy eggs from 5pm p/high");
        TypicalTestTasks.task1.setTaskDetails(new TaskDetails("Buy eggs"));
        TypicalTestTasks.task1.setStartTime(new StartTime("5pm"));
        TypicalTestTasks.task1.setEndTime(new EndTime(""));
        TypicalTestTasks.task1.setPriority(new Priority("high"));
        assertResultMessage(UndoCommand.MESSAGE_SUCCESS);
 
        assertResultMessage(UndoCommand.MESSAGE_SUCCESS);
        commandBox.runCommand("undo");
        assertResultMessage(UndoCommand.MESSAGE_SUCCESS);
    }
    
    @Test
    public void undoThreeChanges() throws IllegalValueException {
        //undo three changes
        commandBox.runCommand("update 1 Attend yoga session from 2pm to 4pm p/high");
        TypicalTestTasks.task1.setTaskDetails(new TaskDetails("Attend yoga session"));
        TypicalTestTasks.task1.setStartTime(new StartTime("2pm"));
        TypicalTestTasks.task1.setEndTime(new EndTime("4pm"));
        TypicalTestTasks.task1.setPriority(new Priority("high")); 
        commandBox.runCommand("delete 2");
        commandBox.runCommand("delete 2");
        commandBox.runCommand("undo");
        commandBox.runCommand("undo");
        assertResultMessage(UndoCommand.MESSAGE_SUCCESS);
        TypicalTestTasks.task1.setTaskDetails(new TaskDetails("Buy eggs"));
        TypicalTestTasks.task1.setStartTime(new StartTime("5pm"));
        TypicalTestTasks.task1.setEndTime(new EndTime(""));
        TypicalTestTasks.task1.setPriority(new Priority("high"));
        assertResultMessage(String.format(UpdateCommand.MESSAGE_UPDATE_TASK_SUCCESS,TypicalTestTasks.task1.getTaskDetails()));
        commandBox.runCommand("undo");
        assertResultMessage(UndoCommand.MESSAGE_SUCCESS);
    }
    
    @Test
    public void undoAddTest() throws IllegalValueException {
    commandBox.runCommand("add Attend Takewando session from 9pm to 10pm p/high");
    TypicalTestTasks.task2.setTaskDetails(new TaskDetails("Attend Takewando session"));
        TypicalTestTasks.task2.setStartTime(new StartTime("9pm"));
        TypicalTestTasks.task2.setEndTime(new EndTime("10pm"));
        TypicalTestTasks.task2.setPriority(new Priority("high")); 
        commandBox.runCommand("undo");
        assertResultMessage(UndoCommand.MESSAGE_SUCCESS);
    }
    
    @Test
    public void undoDeleteTest() throws IllegalValueException {
    commandBox.runCommand("add train for IPPT from 8pm to 9pm p/high");
    TypicalTestTasks.task3.setTaskDetails(new TaskDetails("train for IPPT"));
        TypicalTestTasks.task3.setStartTime(new StartTime("8pm"));
        TypicalTestTasks.task3.setEndTime(new EndTime("9pm"));
        TypicalTestTasks.task3.setPriority(new Priority("high")); 
        commandBox.runCommand("delete 2");
        commandBox.runCommand("undo");
        assertResultMessage(UndoCommand.MESSAGE_SUCCESS);
        
    }
    
    @Test
    public void undoUpdateTest() throws IllegalValueException {
        commandBox.runCommand("update 7 from 10pm p/low");
        TypicalTestTasks.task6.setStartTime(new StartTime("10pm"));
        TypicalTestTasks.task6.setPriority(new Priority("low")); 
        assertResultMessage(String.format(UpdateCommand.MESSAGE_UPDATE_TASK_SUCCESS,TypicalTestTasks.task6.getTaskDetails()));
        commandBox.runCommand("undo");
        assertResultMessage(UndoCommand.MESSAGE_SUCCESS);
    }
    
    @Test
    public void undoDoneTest() {
    	commandBox.runCommand("done 7");
    	assertResultMessage(DoneCommand.MESSAGE_DONE_TASK_SUCCESS);
    	commandBox.runCommand("undo");
    	assertResultMessage(UndoCommand.MESSAGE_SUCCESS);
    }
    
    @Test
    public void undoClearTest() {
    	commandBox.runCommand("clear");
    	assertResultMessage(ClearCommand.MESSAGE_SUCCESS);
    	commandBox.runCommand("undo");
    	assertResultMessage(UndoCommand.MESSAGE_SUCCESS);
    }
    
    /*@Test
    public void undoSetstorageTest() {
        //TODO
    }*/
    
}