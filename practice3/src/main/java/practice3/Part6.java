package practice3;

public class Part6 {

    public static void main(String[] args) {
        String input = Util.getInput("part6.txt");
        System.out.print(convert(input));
    }

    private static int equalsCount(String input, String seachWord) {
        String[] words = input.split("\\s+");
        int equalsCount = 0;
        for (String word : words)
            if (word.equals(seachWord))
                equalsCount++;
        return equalsCount;
    }

    public static String convert(String input) {
        StringBuilder sb = new StringBuilder();
        String[] lines = input.split(System.lineSeparator());
        for (int indexLine = 0; indexLine < lines.length; indexLine++) {
            String[] words = lines[indexLine].split("\\s+");
            for (int indexWords = 0; indexWords < words.length; indexWords++) {
                sb.append((indexWords > 0) ? " " : "").append((equalsCount(input, words[indexWords]) > 1) ? "_" : "").append(words[indexWords]);
            }
            if (indexLine < lines.length - 1)
                sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

}
