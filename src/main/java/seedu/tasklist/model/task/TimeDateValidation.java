package seedu.tasklist.model.task;

import java.util.Calendar;

public class TimeDateValidation {
	
	private Calendar tempCal = Calendar.getInstance();
	private long timeswap;
	private StartTime startTime;
	private EndTime endTime;
	 public TimeDateValidation(StartTime starttime, EndTime endtime){
		 startTime = starttime;
		 endTime = endtime;
    	  notChronological(startTime, endTime);
      }
	 
	private void notChronological(StartTime starttime, EndTime endtime) {
		if(starttime.getAsCalendar().after(endtime.getAsCalendar())){
			System.out.println("YEs out of place !!!!");
    		  tempCal = (Calendar) starttime.getAsCalendar().clone();
    		  startTime = new StartTime(endtime.getAsCalendar().getTimeInMillis());
    		  endTime = new EndTime(tempCal.getTimeInMillis());
    	  }
	}
	
	public StartTime getStartTime(){
		return startTime;
	}
	
	public EndTime getEndTime(){
		return endTime;
	}
	
}
