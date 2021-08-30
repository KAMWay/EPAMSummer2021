package practice6.part2;

import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IntSupplier getInt = scanner::nextInt;
        int n = getInt.getAsInt();
        int k = getInt.getAsInt();

        List<Integer> list = Stream.iterate(0, i -> i + 1).limit(n).collect(Collectors.toList());

        System.out.print("ArrayList#Index: ");
        System.out.println(removeByIndex(new ArrayList<>(list), k) + " ms");
        System.out.print("LinkedList#Index: ");
        System.out.println(removeByIndex(new LinkedList<>(list), k) + " ms");

        System.out.print("ArrayList#Iterator: ");
        System.out.println(removeByIterator(new ArrayList<>(list), k) + " ms");
        System.out.print("LinkedList#Iterator: ");
        System.out.println(removeByIterator(new LinkedList<>(list), k) + " ms");
    }

    public static long removeByIndex(final List<Integer> list, final int k) {
        long time = System.currentTimeMillis();
        int index = 0;
        for (int i = list.size() - 1; i > 0; i--) {
            index += (k - 1);

            while (index >= list.size()) {
                index -= list.size();
            }

            list.remove(index);
        }

        return System.currentTimeMillis() - time;
    }

    public static long removeByIterator(final List<Integer> list, int k) {
        long time = System.currentTimeMillis();
        int count = 0;

        Iterator<Integer> it = list.iterator();
        while (list.size() > 1) {
            if (it.hasNext()) {
                it.next();
                count++;

                if (count == k) {
                    it.remove();
                    count = 0;
                }
            } else {
                it = list.iterator();
            }
        }

        return System.currentTimeMillis() - time;
    }

}
