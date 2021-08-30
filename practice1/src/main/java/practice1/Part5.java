package practice1;

public class Part5 {

    private static int adder(int digits) {
        int add = 0;
        do {
            add += digits % 10;
            digits = digits / 10;
        } while (digits > 0);

        return add;
    }

    public static void main(String[] args) {
        if (Integer.parseInt(args[0]) > 0)
            System.out.print(adder(Integer.parseInt(args[0])));
    }

}