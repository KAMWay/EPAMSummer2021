package practice6.part3;

import java.util.Scanner;
import java.util.function.IntSupplier;

public class Part3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IntSupplier getInt = scanner::nextInt;
        Parking parking = new Parking(getInt.getAsInt());

        int[] input = new int[]{2, 3, 2, 2, 2, 1, 1};
        for (int i : input) {
            boolean flag;
            if (i > 1) {
                flag = parking.arrive(i);

            } else {
                flag = parking.depart(1);
            }
            parking.print();
            System.out.println(", " + flag);
        }
    }

}
