package practice1;

public class Part3 {

    private static void displayArgsInConsole(String[] args) {
        if (args != null) {
            int count = 0;
            for (String arg : args) {
                count++;
                System.out.print(arg);
                if (count != args.length) System.out.print(" ");
            }
        }
    }

    public static void main(String[] args) {
        displayArgsInConsole(args);
    }

}