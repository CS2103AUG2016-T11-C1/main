package guitests;

import guitests.guihandles.PersonCardHandle;

import org.junit.Test;

import seedu.tasklist.commons.core.Messages;
import seedu.tasklist.commons.exceptions.IllegalValueException;
import seedu.tasklist.logic.commands.UndoCommand;
import seedu.tasklist.model.task.EndTime;
import seedu.tasklist.model.task.Priority;
import seedu.tasklist.model.task.StartTime;
import seedu.tasklist.model.task.TaskDetails;
import seedu.tasklist.testutil.TestTask;
import seedu.tasklist.testutil.TestUtil;
import seedu.tasklist.testutil.TypicalTestTasks;

public class UndoCommandTest extends TaskListGuiTest {
    
    @Test
    public void undoOneChange() throws IllegalValueException {
        TestTask[] currentList = td.getTypicalTasks();
        //undo a change that was never made
        commandBox.runCommand("update 20 Buy eggs");
        commandBox.runCommand("undo");
        assertResultMessage(UndoCommand.MESSAGE_FAILURE);
        //undo one change
        commandBox.runCommand("update 1 Attend yoga session from 2pm to 4pm p/high");
        TypicalTestTasks.task1.setTaskDetails(new TaskDetails("Attend yoga session"));
        TypicalTestTasks.task1.setStartTime(new StartTime("2pm"));
        TypicalTestTasks.task1.setEndTime(new EndTime("4pm"));
        TypicalTestTasks.task1.setPriority(new Priority("high")); 
        commandBox.runCommand("undo");
        TypicalTestTasks.task1.setTaskDetails(new TaskDetails("Buy eggs"));
        TypicalTestTasks.task1.setStartTime(new StartTime("5pm"));
        TypicalTestTasks.task1.setEndTime(new EndTime("-"));
        TypicalTestTasks.task1.setPriority(new Priority("high"));
        assertResultMessage(UndoCommand.MESSAGE_SUCCESS);
    }
    
    @Test
    public void undoTwoChanges() {
        //TODO
    }
    
    @Test
    public void undoThreeChanges() {
        //TODO
    }
    
    @Test
    public void undoAddTest() {
        //TODO
    }
    
    @Test
    public void undoDeleteTest() {
        //TODO
    }
    
    @Test
    public void undoUpdateTest() {
        //TODO
    }
    
    @Test
    public void undoDoneTest() {
        //TODO
    }
    
    @Test
    public void undoClearTest() {
        //TODO
    }
    
    @Test
    public void undoSetstorageTest() {
        //TODO
    }
    
}