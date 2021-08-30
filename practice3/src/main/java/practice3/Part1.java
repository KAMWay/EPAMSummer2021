package practice3;

import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    private static final Pattern mainPattern = Pattern.compile("(?m)^(\\S+);(\\S+)\\s(\\S+);((\\S+)@(\\S+))$");

    public static void main(String[] args) {
        String input = Util.getInput("part1.txt");
        System.out.println(convert1(input));
        System.out.println(convert2(input));
        System.out.println(convert3(input));
        System.out.println(convert4(input));
    }

    public static String convert1(String input) {
        StringBuilder sb = new StringBuilder();
        String[] lines = input.split(System.lineSeparator());
        for (String line : lines) {
            Matcher matcher = mainPattern.matcher(line);
            if (matcher.matches()) {
                sb.append(matcher.group(1));
                sb.append(": ");
                sb.append(matcher.group(4));
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public static String convert2(String input) {
        StringBuilder sb = new StringBuilder();
        String[] lines = input.split(System.lineSeparator());
        for (String line : lines) {
            Matcher matcher = mainPattern.matcher(line);
            if (matcher.matches()) {
                sb.append(matcher.group(3));
                sb.append(" ");
                sb.append(matcher.group(2));
                sb.append(" (email: ");
                sb.append(matcher.group(4));
                sb.append(")");
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public static String getDomains(String input) {
        StringBuilder sb = new StringBuilder();
        String[] lines = input.split(System.lineSeparator());
        for (String line : lines) {
            Matcher matcher = mainPattern.matcher(line);
            if (matcher.matches()) {
                String domain = matcher.group(4).split("@")[1];
                if (sb.indexOf(domain) == -1) sb.append(domain).append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public static String convert3(String input) {
        StringBuilder sb = new StringBuilder();
        String[] lines = input.split(System.lineSeparator());
        String[] domains = getDomains(input).split(System.lineSeparator());
        for (String domain : domains) {
            sb.append(domain).append(" ==> ");
            for (String line : lines) {
                Matcher matcher = mainPattern.matcher(line);
                if (matcher.matches()) {
                    String curDomain = matcher.group(4).split("@")[1];
//                    "(?<=@)\\S+" <--look back
//                    \\b[a-zA-Zа-яА-ЯёЁ]\\b
//                    [a-zA-Zа-яА-ЯёЁ\p{L}];
//                    \\p{L}+
                    if (curDomain.equals(domain)) {
                        if (sb.lastIndexOf(" ==> ") != sb.length() - 5) sb.append(", ");
                        sb.append(matcher.group(1));
                    }
                }
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public static String convert4(String input) {
        StringBuilder sb = new StringBuilder();
        String[] lines = input.split(System.lineSeparator());
        for (String line : lines) {
            Matcher matcher = mainPattern.matcher(line);
            if (matcher.matches()) {
                sb.append(matcher.group());
                sb.append(";");
                SecureRandom random = new SecureRandom();
                int password = random.nextInt(10000 - 1000);
                password += 1000;
                sb.append(password);
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}