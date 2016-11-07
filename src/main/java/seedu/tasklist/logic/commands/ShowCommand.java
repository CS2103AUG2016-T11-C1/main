//@@author A0142102E
package seedu.tasklist.logic.commands;

import java.util.List;

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;

import seedu.tasklist.logic.parser.TimePreparser;

/**
 * Shows all tasks that fulfill the category keyword.
 * Keyword matching is case insensitive.
 */
public class ShowCommand extends Command {

	public static final String COMMAND_WORD = "show";
	public static final String TODAY_WORD = "today";
	public static final String TOMORROW_WORD = "tomorrow";
    public static final String OVERDUE_WORD = "overdue";
    public static final String FLOATING_WORD = "floating";
	

	public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows all tasks under the requested category. "
			+ "The specified keywords (case-insensitive) are shown as a list with index numbers.\n"
			+ "Parameters: KEYWORD (all, incomplete, complete, p/[PRIORITY]\n"
			+ "Example: " + COMMAND_WORD + " all";

	public static final String MESSAGE_SHOW_FAILURE = "Invalid category. Available categories: all, incomplete, complete, p/[PRIORITY], or a date";
	public static final String MESSAGE_SUCCESS = "Shown requested tasks.";
	private final String keyword;

	public ShowCommand(String keyword) {
		this.keyword = keyword.toLowerCase();
	}

	@Override
	public CommandResult execute() {

		switch (keyword) {

		case "all":
			model.updateFilteredListToShowAll(); break;

		case "incomplete": case "": case "upcoming":
			model.updateFilteredListToShowIncomplete(); break;

		case "complete": case "done":
			model.updateFilteredListToShowComplete(); break;

		case "p/high": case "p/med": case "p/low":
			model.updateFilteredListToShowPriority(keyword); break;

		case "floating":
			model.updateFilteredListToShowFloating(); break;

		case "overdue":
			model.updateFilteredListToShowOverDue(); break;
			
		case "recurring":
			model.updateFilteredListToShowRecurring(); break;

		default:
			String parsedKeyword = TimePreparser.preparse(keyword);
			List<DateGroup> dates = new Parser().parse(parsedKeyword);
			if(dates.isEmpty()){
				return new CommandResult(String.format(MESSAGE_SHOW_FAILURE));
			}
			else{
				model.updateFilteredListToShowDate(parsedKeyword);
			}
		}
		return new CommandResult(String.format(getMessageForTaskListShownSummary(model.getFilteredTaskList().size())));
	}
}