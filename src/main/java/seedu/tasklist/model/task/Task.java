package seedu.tasklist.model.task;

import java.util.Objects;

import seedu.tasklist.commons.util.CollectionUtil;
import seedu.tasklist.model.tag.UniqueTagList;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Task implements ReadOnlyTask{

	private static int currentID = 0;
	
    private TaskDetails name;
    private StartTime startTime;
    private StartDate startDate;
    private EndTime endTime;
    private EndDate endDate;
    private Priority priority;
    private DateTime dateTime;
    private int uniqueID;
    private boolean isComplete;

    private UniqueTagList tags;

    /**
     * Every field must be present and not null.
     */
    public Task(TaskDetails name, StartTime startTime, StartDate startDate, EndTime endTime, EndDate endDate, Priority priority, UniqueTagList tags) {
        assert !CollectionUtil.isAnyNull(name, startTime, startDate, endTime, endDate, priority, tags);
        this.name = name;
        this.startTime = startTime;
        this.startDate = startDate;
        this.endTime = endTime;
        this.endDate = endDate;
        this.priority = priority;
        dateTime = new DateTime(startTime, startDate, endTime, endDate);
        this.uniqueID = currentID++;
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
        this.isComplete = false;
    }

    /**
     * Copy constructor.
     */
    public Task(ReadOnlyTask source) {
        this(source.getName(), source.getStartTime(), source.getStartDate(), source.getEndTime(), source.getEndDate(), source.getPriority(), source.getTags());
    }

    @Override
    public TaskDetails getName() {
        return name;
    }

    @Override
    public StartTime getStartTime() {
        return startTime;
    }
    
    @Override
    public StartDate getStartDate(){
    	return startDate;
    }
    
    @Override
    public EndTime getEndTime() {
        return endTime;
    }
    
    @Override
    public EndDate getEndDate(){
    	return endDate;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }
    
    @Override
    public int getUniqueID() {
        return uniqueID;
    }
    
    public void setUniqueId(int newuniqueId){
    	uniqueID = newuniqueId;
    }

    @Override
    public UniqueTagList getTags() {
        return new UniqueTagList(tags);
    }
    
    @Override
    public DateTime getDateTime() {
        return dateTime;
    }

    /**
     * Replaces this person's tags with the tags in the argument tag list.
     */
    public void setTags(UniqueTagList replacement) {
        tags.setTags(replacement);
    }

    @Override
    public boolean isComplete() {
        return isComplete;
    }
    
    public void markAsComplete() {
        isComplete = true;
    }
    
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyTask // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyTask) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, startTime, endTime, priority, uniqueID, tags);
    }

    @Override
    public String toString() {
        return getAsText();
    }
    
}
