package seedu.tasklist.model.task;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import seedu.tasklist.commons.exceptions.IllegalValueException;
import seedu.tasklist.logic.commands.TimePreparser;

import com.joestelmach.natty.*;

/**
 * Represents the start time of a task.
 * Guarantees: immutable; is valid as declared in {@link #isValidStartTime(String)}
 */
public class StartTime {

    public static final String MESSAGE_START_TIME_CONSTRAINTS = "Start time is invalid!";

    public final Calendar starttime; 

    /**
     * Validates given start time.
     *
     * @throws IllegalValueException if given start time is invalid.
     */
    public StartTime(String input) throws IllegalValueException {
    	starttime = Calendar.getInstance();
    	String startTime = TimePreparser.preparse(input);
    	if(!startTime.isEmpty() && !startTime.equals(new Date(0).toString())){
    		List<DateGroup> dates = new Parser().parse(startTime);
    		if(dates.isEmpty()){
    			throw new IllegalValueException("Start time is invalid!");
    		}
    		else if(dates.get(0).getDates().isEmpty()){
    			throw new IllegalValueException("Start time is invalid!");
    		}
    		else{
    			starttime.setTime(dates.get(0).getDates().get(0));
    		}
    	}
    	else{
    		starttime.setTime(new Date(0));
    	}
    }

    @Override
    public String toString() {
    	if(starttime.getTime().equals(new Date(0))){
    		return (new Date(0)).toString();
    	}
    	else{
    		return starttime.getTime().toString();
    	}
    }
    
    public String toCardString() {
    	if(starttime.equals(new Date(0))){
    		return "-";
    	}
    	else{
    		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    		Date startTimeString = starttime.getTime();
    		String finalStartString = df.format(startTimeString );
    		int hrs;
    		String build;
    		String[] store = new String [2];
    		String[] store2 = new String [2];
    		store = finalStartString.split(" ");
    		build = store[0]; 
    		store2 = store[1].split(":");
    		hrs= Integer.valueOf(store2[0]);
    		if(hrs>12){
    			hrs -=12;
    			build += " "+hrs+"p.m.";
    		}
    		else{
    			build += " "+hrs+"a.m.";
    		}
    		return build;
    		
    	}
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (this.starttime != null && ((StartTime) other).starttime != null ) && (other instanceof StartTime // instanceof handles nulls
                && this.starttime.equals(((StartTime) other).starttime)); // state check
    }

    @Override
    public int hashCode() {
        return starttime.hashCode();
    }

}
