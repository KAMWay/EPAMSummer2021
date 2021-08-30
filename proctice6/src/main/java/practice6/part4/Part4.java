package practice6.part4;

public class Part4 {

    public static void main(String[] args) {
        System.out.println("Order:");
        Range range = new Range(3, 10);
        for (Integer el : range) {
            System.out.printf("%d ", el);
        }
        System.out.println();

        System.out.println("ReversedOrder:");
        range = new Range(3, 10, true);
        for (Integer el : range) {
            System.out.printf("%d ", el);
        }
    }

}
