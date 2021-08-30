package practice4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public static void main(String[] args) {
        String input = Demo.getInput("part1.txt");
        System.out.println(convert(input));
    }

    public static String convert(String input) {
        Pattern pattern = Pattern.compile("\\S+");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            if (matcher.group().length() > 3)
                input = input.replace(matcher.group(), matcher.group().substring(2));
        }
        return input;
    }

}
