package practice4;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

    private static String getRegex(String str) {
        switch (str) {
            case "char":
                return ("(?<= |^|\\s)([a-zA-Zа-яА-ЯёЁ])(?= |$)");
            case "int":
                return "[+-]?(?<!\\.)\\b[0-9]+\\b(?!\\.[0-9])";
            case "double":
                return "[0-9]*[.,][0-9]+";
            case "String":
                return "[a-zA-Zа-яА-ЯёЁ]{2,}";
            default:
                return "";
        }
    }

    public static String getInput(String input, String regex) {
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            sb.append(matcher.group()).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String input = Demo.getInput("part3.txt");
        Scanner scanner = new Scanner(System.in);
        String type;
        while (scanner.hasNextLine()) {
            type = scanner.next();
            if (type.equals("stop")) break;
            String regex = getRegex(type);
            if (regex.equals("")) System.out.println("Incorrect input");
            else System.out.println(getInput(input, regex));
        }
    }

}