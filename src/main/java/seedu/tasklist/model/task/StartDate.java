package seedu.tasklist.model.task;

import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import seedu.tasklist.commons.exceptions.IllegalValueException;

public class StartDate {

	    public static final String MESSAGE_STARTDATE_CONSTRAINTS = "Please enter a valid date in this format: dd/mm/yyyy!";
	    public static final String STARTDATE_VALIDATION_REGEX = "([0-3][0-9]{1}+)(/|-|)([0-1][0-9]{1}+)(/|-|)([1-2][0-9][0-9][0-9]{1}+)";

	    public final Calendar cal;

	    /**
	     * Validates given phone number.
	     *
	     * @throws IllegalValueException if given phone string is invalid.
	     */
	    public StartDate(String startDate) throws IllegalValueException {
	     //  assert phone != null;
	        if (startDate==null||!isValidStartDate(startDate)) {
	            throw new IllegalValueException(MESSAGE_STARTDATE_CONSTRAINTS);
	        }
	        
	    	cal = Calendar.getInstance();
	    	String[] dateParameters = new String[3];
	    	
	    	dateParameters = startDate.split("(/|-| )");
	   
	    	cal.set(Integer.valueOf(dateParameters[2]), Integer.valueOf(dateParameters[1]),Integer.valueOf(dateParameters[0]));
	    	
	    }

	    /**
	     * Returns true if a given string is a valid person phone number.
	     */
	    public static boolean isValidStartDate(String test) throws IllegalValueException{
	    	if(test==null){
	    		return true;
	    	}
	    	
	    	//checks whether the date itself is valid or not
	    	 SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    	    dateFormat.setLenient(false);
	    	    try {
	    	      dateFormat.parse(test.trim());
	    	    } catch (ParseException pe) {
	    	      return false;
	    	    }
	    	    
	    	    return test.matches(STARTDATE_VALIDATION_REGEX); //checks for date format usign regex
	
	    }

	    @Override
	    public String toString() {
	        return cal.toString();
	    }

	    @Override
	    public boolean equals(Object other) {
	        return  (other instanceof StartDate // instanceof handles nulls
	                && this.cal.equals(((StartDate) other).cal)); // state check
	    }

	    @Override
	    public int hashCode() {
	        return cal.hashCode();
	    }
}
