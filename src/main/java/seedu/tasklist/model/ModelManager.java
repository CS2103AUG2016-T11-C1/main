package seedu.tasklist.model;

import javafx.collections.transformation.FilteredList;
import seedu.tasklist.commons.core.ComponentManager;
import seedu.tasklist.commons.core.LogsCenter;
import seedu.tasklist.commons.core.UnmodifiableObservableList;
import seedu.tasklist.commons.events.model.TaskListChangedEvent;
import seedu.tasklist.commons.util.StringUtil;
import seedu.tasklist.model.task.ReadOnlyTask;
import seedu.tasklist.model.task.Task;
import seedu.tasklist.model.task.UniqueTaskList;
import seedu.tasklist.model.task.UniqueTaskList.PersonNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents the in-memory model of the address book data.
 * All changes to any model should be synchronized.
 */
public class ModelManager extends ComponentManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TaskList taskList;
    private final FilteredList<Task> filteredTasks;

    /**
     * Initializes a ModelManager with the given AddressBook
     * AddressBook and its variables should not be null
     */
    public ModelManager(TaskList src, UserPrefs userPrefs) {
        super();
        assert src != null;
        assert userPrefs != null;

        logger.fine("Initializing with address book: " + src + " and user prefs " + userPrefs);

        taskList = new TaskList(src);
        filteredTasks = new FilteredList<>(taskList.getTasks());
    }

    public ModelManager() {
        this(new TaskList(), new UserPrefs());
    }

    public ModelManager(ReadOnlyTaskList initialData, UserPrefs userPrefs) {
        taskList = new TaskList(initialData);
        filteredTasks = new FilteredList<>(taskList.getTasks());
    }

    @Override
    public void resetData(ReadOnlyTaskList newData) {
        taskList.resetData(newData);
        indicateAddressBookChanged();
    }

    @Override
    public ReadOnlyTaskList getTaskList() {
        return taskList;
    }

    /** Raises an event to indicate the model has changed */
    private void indicateAddressBookChanged() {
        raise(new TaskListChangedEvent(taskList));
    }

    @Override
    public synchronized void deleteTask(ReadOnlyTask target) throws PersonNotFoundException {
        taskList.removePerson(target);
        updateFilteredListToShowIncomplete();
        indicateAddressBookChanged();
    }

    @Override
    public synchronized void addTask(Task task) throws UniqueTaskList.DuplicatePersonException {
        taskList.addPerson(task);
        updateFilteredListToShowIncomplete();
        indicateAddressBookChanged();
    }
    
    @Override
    public synchronized void markTaskAsComplete(ReadOnlyTask task) throws PersonNotFoundException {
        taskList.markTaskAsComplete(task);
        updateFilteredListToShowIncomplete();
        indicateAddressBookChanged();
    }

    //=========== Filtered Person List Accessors ===============================================================

    @Override
    public UnmodifiableObservableList<ReadOnlyTask> getFilteredPersonList() {
        return new UnmodifiableObservableList<>(filteredTasks);
    }
    
    public UnmodifiableObservableList<Task> getModifiableTaskList() {
        return new UnmodifiableObservableList<>(filteredTasks);
    }

    @Override
    public void updateFilteredListToShowAll() {
        filteredTasks.setPredicate(null);
    }
    
    @Override
    public void updateFilteredListToShowIncomplete() {
    	updateFilteredListToShowAll();
    	updateFilteredTaskList(new PredicateExpression(new DefaultDisplayQualifier()));
    }

    @Override
    public void updateFilteredTaskList(Set<String> keywords){
        updateFilteredTaskList(new PredicateExpression(new NameQualifier(keywords)));
    }

    private void updateFilteredTaskList(Expression expression) {
        filteredTasks.setPredicate(expression::satisfies);
    }
    
    public Set<String> getKeywordsFromList(List<ReadOnlyTask> tasks){
        Set<String> keywords = new HashSet<String>();
        for(ReadOnlyTask task: tasks){
            keywords.addAll(Arrays.asList(task.getTaskDetails().toString().split(" ")));
        }
        return keywords;
    }

    //========== Inner classes/interfaces used for filtering ==================================================

    interface Expression {
        boolean satisfies(ReadOnlyTask person);
        String toString();
    }

    private class PredicateExpression implements Expression {

        private final Qualifier qualifier;

        PredicateExpression(Qualifier qualifier) {
            this.qualifier = qualifier;
        }

        @Override
        public boolean satisfies(ReadOnlyTask person) {
            return qualifier.run(person);
        }

        @Override
        public String toString() {
            return qualifier.toString();
        }
    }

    interface Qualifier {
        boolean run(ReadOnlyTask person);
        String toString();
    }
    
    private class DefaultDisplayQualifier implements Qualifier {

    	DefaultDisplayQualifier(){
    		
    	}
    	
		@Override
		public boolean run(ReadOnlyTask person) {
			return !person.isComplete();
		}
    }

    private class NameQualifier implements Qualifier {
        private Set<String> nameKeyWords;
        private Pattern NAME_QUERY;
        

        NameQualifier(Set<String> nameKeyWords) {
            this.nameKeyWords = nameKeyWords;
            this.NAME_QUERY = Pattern.compile(getRegexFromString());
        }

        private String getRegexFromString(){
        	String result = "";
        	for(String keyword: nameKeyWords){
        		for(char c: keyword.toCharArray()){
        			switch(c){
        			case '*':
        				result += ".*";
        				break;
        			default:
        				result += c;
        			}
        		}
        	}
        	return result;
        }
        
        @Override
        public boolean run(ReadOnlyTask person) {
//            return nameKeyWords.stream()
//                    .filter(keyword -> StringUtil.containsIgnoreCase(person.getName().fullName, keyword))
//                    .findAny()
//                    .isPresent();
        	Matcher matcher = NAME_QUERY.matcher(person.getTaskDetails().taskDetails);
        	return matcher.matches() && !person.isComplete();
        }
        
        @Override
        public String toString() {
            return "name=" + String.join(", ", nameKeyWords);
        }
    }
    
}