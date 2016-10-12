package seedu.tasklist.model.task;

import java.util.Calendar;
import java.util.Locale;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import seedu.tasklist.commons.exceptions.IllegalValueException;

public class EndDate {

	    public static final String MESSAGE_ENDDATE_CONSTRAINTS = "Please enter a valid date in this format: dd/mm/yyyy!";
	    public static final String ENDDATE_VALIDATION_REGEX = "(([0-3][0-9]{1}+)(/|-)([0-1][0-9]{1}+)(/|-)([1-2][0-9][0-9][0-9]{1}+))";

	    public final Calendar cal2;
	    public String value = "na";
	    /**
	     * Validates given phone number.
	     *
	     * @throws IllegalValueException if given phone string is invalid.
	     */
	    public EndDate(String endDate) throws IllegalValueException {
	     //  assert phone != null;
	        if (endDate!=null&&!isValidEndDate(endDate)) {
	            throw new IllegalValueException(MESSAGE_ENDDATE_CONSTRAINTS);
	        }
	        
	    	cal2 = Calendar.getInstance();
	    	String[] dateParameters = {"0", "0", "0"};
	    	
	    	if(endDate!=null){
	    	dateParameters = endDate.split("(/|-)");
	    	cal2.set(Integer.valueOf(dateParameters[2]), (Integer.valueOf(dateParameters[1])-1),Integer.valueOf(dateParameters[0]));	    	
	        value = endDate;
	    	}
	    }

	    /**
	     * Returns true if a given string is a valid person phone number.
	     */
	    public static boolean isValidEndDate(String test) throws IllegalValueException{
	    	if(test==null){
	    		return true;
	    	}
	    	
	    	//checks whether the date itself is valid or not
	    	 SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.CHINA);
	    	    dateFormat.setLenient(false);
	    	    try {
	    	      dateFormat.parse(test.trim());
	    	    } catch (ParseException pe) {
	    	      return false;
	    	    }
	    	    
	    	    return test.matches(ENDDATE_VALIDATION_REGEX); //checks for date format usign regex
	
	    }

	    @Override
	    public String toString() {
	        return value;
	    }

	    @Override
	    public boolean equals(Object other) {
	        return  (other instanceof EndDate // instanceof handles nulls
	                && this.cal2.equals(((EndDate) other).cal2)); // state check
	    }

	    @Override
	    public int hashCode() {
	        return cal2.hashCode();
	    }
}