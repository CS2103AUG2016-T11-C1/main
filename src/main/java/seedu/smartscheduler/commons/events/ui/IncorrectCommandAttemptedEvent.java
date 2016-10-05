package seedu.smartscheduler.commons.events.ui;

import seedu.smartscheduler.commons.events.BaseEvent;
import seedu.smartscheduler.logic.commands.Command;

/**
 * Indicates an attempt to execute an incorrect command
 */
public class IncorrectCommandAttemptedEvent extends BaseEvent {

    public IncorrectCommandAttemptedEvent(Command command) {}

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
