package practice1;

public class Part2 {

    private static int additionNumbers(String[] args) {
        int sumNumbers = 0;
        int number = 0;
        for (String arg : args) {
            try {
                number = Integer.parseInt(arg);
            } catch (Exception e) {
                number = 0;
            } finally {
                sumNumbers += number;
            }
        }

        return sumNumbers;
    }

    public static void main(String[] args) {
        System.out.print(additionNumbers(args));
    }

}