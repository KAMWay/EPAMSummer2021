package practice3;

public class Part3 {

    public static void main(String[] args) {
        String input = Util.getInput("part3.txt");
        System.out.print(convert(input));
    }

    private static String convertedFirstCharRegister(String word) {
        char ch = word.charAt(0);
        return (Character.isLowerCase(ch) ?
                String.valueOf(ch).toUpperCase() : String.valueOf(ch).toLowerCase())
                + word.substring(1);
    }

    public static String convert(String input) {
        StringBuilder sb = new StringBuilder();
        String[] lines = input.split(System.lineSeparator());
        int indexLine = 0;
        for (String line : lines) {
            String[] words = line.split("[\\s,'.-]+");
            for (String word : words) {
                if (word.length() > 2) word = convertedFirstCharRegister(word);
                sb.append((sb.length() != 0 && sb.lastIndexOf("\n") != sb.length() - 1) ? " " : "").append(word);
            }
            if (indexLine++ < lines.length - 1) sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
