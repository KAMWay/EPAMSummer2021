package practice1;

public class Part4 {

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        if (args.length >= 2 && Integer.parseInt(args[0]) > 0 && Integer.parseInt(args[1]) > 0)
            System.out.print(gcd(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
    }

}