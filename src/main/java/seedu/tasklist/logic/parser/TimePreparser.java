package seedu.tasklist.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimePreparser {

    public static String preparse(String input) {
        String[] tokens = input.split(" ");
        String result = "";
        if (tokens.length >= 1) {
            Pattern dateType = Pattern
                    .compile("(?<day>[012][0-9]|3[01])[/.-](?<month>[0]?[1-9]|1[012])[/.-](?<year>(19|20)\\d\\d)");
            for (String token : tokens) {
                Matcher matcher = dateType.matcher(token);
                if (matcher.matches()) {
                	System.out.println("token: "+token);
                    String rearrangedDate = matcher.group("month") + "/" + matcher.group("day") + "/"
                            + matcher.group("year");
                    System.out.println("rearrangedDate: "+rearrangedDate);
                    token = rearrangedDate;
                }   
                result +=  token + " ";
            }
        } 
        else {
            result = tokens[0];
        }
        return result.trim();
    }
}
