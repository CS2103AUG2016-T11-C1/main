package guitests;

import org.junit.Test;

import seedu.tasklist.commons.core.Messages;
import seedu.tasklist.testutil.TestTask;
import seedu.tasklist.testutil.TypicalTestTasks;

import static org.junit.Assert.assertTrue;

public class ShowCommandTest extends TaskListGuiTest {

    @Test
    public void show_nonEmptyList() {
        assertShowResult("show all", TypicalTestTasks.task1, TypicalTestTasks.task2, TypicalTestTasks.task3, TypicalTestTasks.task4, TypicalTestTasks.task5, TypicalTestTasks.task6, TypicalTestTasks.task7); //no results
        
        //show after deleting one result
        commandBox.runCommand("delete 1");
        assertShowResult("show all", TypicalTestTasks.task2, TypicalTestTasks.task3, TypicalTestTasks.task4, TypicalTestTasks.task5, TypicalTestTasks.task6, TypicalTestTasks.task7);
    }
    
    @Test
    public void show_completedList() {
        commandBox.runCommand("done 1");
        assertShowResult("show complete", TypicalTestTasks.task2);
    }
    
    @Test
    public void show_uncompletedList() {
        assertShowResult("show incomplete", TypicalTestTasks.task3, TypicalTestTasks.task4, TypicalTestTasks.task5, TypicalTestTasks.task6, TypicalTestTasks.task7);        
    }
    
    @Test
    public void show_datedList() {
    	assertShowResult("show 14 nov", TypicalTestTasks.task3, TypicalTestTasks.task5, TypicalTestTasks.task7);        
    }
    
    @Test
    public void show_priorityList() {
        assertShowResult("show p/high", TypicalTestTasks.task3);
    }

    @Test
    public void show_emptyList(){
        commandBox.runCommand("clear");
        assertShowResult("show all"); //no results
    }

    @Test
    public void show_invalidCommand_fail() {
        commandBox.runCommand("showall");
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);
    }

    private void assertShowResult(String command, TestTask... expectedHits ) {
        commandBox.runCommand(command);
        assertListSize(expectedHits.length);
        assertResultMessage(expectedHits.length + " task(s) listed!");
        assertTrue(taskListPanel.isListMatching(expectedHits));
    }
}