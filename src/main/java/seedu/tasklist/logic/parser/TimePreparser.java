package seedu.tasklist.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimePreparser {
	
	public static String preparse(String input){
		String[] tokens = input.split(" ");
		String result = "";
		Pattern dateType = Pattern.compile("(?<day>[12][0-9]|3[01])[/-](?<month>[1-9]|1[012])[/-](?<year>(19|20)\\d\\d)");
		for(String token: tokens){
			Matcher matcher = dateType.matcher(token);
			if(matcher.matches()){
				String rearrangedDate = matcher.group("month") + "/" + matcher.group("day")
									+ "/" + matcher.group("year");
				token = rearrangedDate;
			}
			result += token+" ";
		}
		return result.trim();
	}
}
