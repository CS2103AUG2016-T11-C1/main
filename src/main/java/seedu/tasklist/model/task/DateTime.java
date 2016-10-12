package seedu.tasklist.model.task;

import java.util.Calendar;

public class DateTime {
    protected EndDate endDate;
    protected StartDate startDate;
    protected StartTime startTime;
    protected EndTime endTime;
    protected Calendar calBegin;
    protected Calendar calEnd;
    private int timeHrs = 0;
    private int timeMins = 0;
    
    public DateTime(StartTime startTime, StartDate startDate, EndTime endTime, EndDate endDate){
    	this.startTime = startTime;
    	this.startDate = startDate;
    	this.endTime =  endTime;
    	this.endDate = endDate;
    	calBegin = startDate.cal;
    	calEnd = endDate.cal2;
    	
    } 
    
    public void check24hrFormat(Time time){
    	String checkTime = time.value;
    	String[] timeComponent = new String[2];
    	if(checkTime.length()==4){
    		timeComponent = checkTime.split(":");
    	    timeHrs = Integer.valueOf(timeComponent[0]);
    	    timeMins = Integer.valueOf(timeComponent[1]);
    	}
    	else{
    		if(checkTime.matches("(pm)"))
               timeHrs = Integer.valueOf(checkTime.charAt(0)) + 12;
    		else 
    			timeHrs = Integer.valueOf(checkTime.charAt(0));
    	}
    }
    
    public void setCalBegin(){
    	check24hrFormat(startTime);
    	calBegin = startDate.cal;
    	calBegin.set(Calendar.HOUR_OF_DAY, timeHrs);
    	calBegin.set(Calendar.MINUTE,timeMins);
    }
    
    public void setCalEnd(){
    	check24hrFormat(endTime);
    	calEnd = endDate.cal2;
    	calEnd.set(Calendar.HOUR_OF_DAY, timeHrs);
    	calEnd.set(Calendar.MINUTE,timeMins);
    }
    
    public Calendar getBeginCal(){
       return calBegin;
    }
    
    public Calendar getEndCal(){
    	return calEnd;
    }
    
    public String beginCalString(){
    	return calBegin.toString();
    }
    
    public String endCalString(){
    	return calEnd.toString();
    }
}
