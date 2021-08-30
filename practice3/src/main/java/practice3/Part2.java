package practice3;

public class Part2 {

    public static void main(String[] args) {
        String input = Util.getInput("part2.txt");
        System.out.println(convert(input));
    }

    private static int lengthComparison(String input, int flag) {
        if (flag < 0) flag = -1;
        else flag = 1;
        int length = 0;
        String[] words = input.split("[\\s,'.-]+");
        for (String word : words) {
            int curLength = word.length();
            if (length == 0 || flag * length > flag * curLength) {
                length = curLength;
            }
        }
        return length;
    }

    public static String convert(String input) {
        StringBuilder minString = new StringBuilder();
        StringBuilder maxString = new StringBuilder();
        int minLength = lengthComparison(input, 1);
        int maxLength = lengthComparison(input, -1);
        String[] words = input.split("[\\s,'.-]+");
        for (String word : words) {
            int length = word.length();
            if (length == minLength && minString.indexOf(word) == -1)
                minString.append((minString.length() == 0) ? "" : ", ").append(word);

            if (length == maxLength && maxString.indexOf(word) == -1)
                maxString.append((maxString.length() == 0) ? "" : ", ").append(word);
        }
        return "Min: " + minString + System.lineSeparator() +
                "Max: " + maxString;
    }
}
