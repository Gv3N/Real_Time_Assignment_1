package com.baktajivan;
//reference from Jsoup from Sir Zhamri
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatch {
    boolean regexMatch(String pattern, String link){
        Pattern patterns = Pattern.compile(pattern);
        Matcher matchers = patterns.matcher(link);
        return matchers.find();
    }//regexMatch
}
