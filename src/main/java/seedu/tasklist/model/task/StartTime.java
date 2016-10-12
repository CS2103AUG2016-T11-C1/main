package seedu.tasklist.model.task;

import seedu.tasklist.commons.exceptions.IllegalValueException;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class StartTime implements Time{

    public static final String MESSAGE_PHONE_CONSTRAINTS = "Person phone numbers should only contain numbers";
    public static final String PHONE_VALIDATION_REGEX = "(([0])?[0-9]{1}+(pm|am))|([1])([0-1]){1}+(pm|am)|([0-1][0-9]:[0-5][0-9])|([2][0-3]:[0-5][0-9])";

    public final String value;
  
  
    /**
     * Validates given phone number.
     *
     * @throws IllegalValueException if given phone string is invalid.
     */
    public StartTime(String phone) throws IllegalValueException {
     //  assert phone != null;
    	if(phone.equals("")){
        	this.value = "";
        }
    	else{
        if (phone==null||!isValidTime(phone)) {
            throw new IllegalValueException(MESSAGE_PHONE_CONSTRAINTS);
        }
    	
        this.value = phone;
    	}

    	
    }

    /**
     * Returns true if a given string is a valid person phone number.
     */
    public boolean isValidTime(String test) {
    	if(test==null){
    		return true;
    	}
    	return test.matches(PHONE_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (this.value != null && ((StartTime) other).value != null ) && (other instanceof StartTime // instanceof handles nulls
                && this.value.equals(((StartTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
