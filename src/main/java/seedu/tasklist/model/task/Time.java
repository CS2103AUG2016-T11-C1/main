package seedu.tasklist.model.task;

public interface Time {
	public String value = "";
	
	public boolean isValidTime(String test);
	
	public String toString();
	
	public boolean equals(Object other);
	
	public int hashCode();
	
}
