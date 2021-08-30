package practice1;

public class Part6 {

    private static boolean isSimple(int number) {
        for (int i = 2; i < Math.round(Math.sqrt(number)) + 1; i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    private static int[] createSimpleSequence(int count) {
        int[] sequence = new int[count];
        int currentCount = 0;
        int number = 2;
        while (currentCount < count) {
            if (isSimple(number)) {
                sequence[currentCount] = number;
                currentCount += 1;
            }
            number += 1;
        }

        return sequence;
    }

    private static void displayIntArrayInConsole(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) System.out.print(" ");
        }
    }

    public static void main(String[] args) {
        if (Integer.parseInt(args[0]) > 0)
            displayIntArrayInConsole(createSimpleSequence(Integer.parseInt(args[0])));
    }

}