package practice6.part3;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parking {

    private final List<Integer> list;

    public Parking(int capacity) {
        this.list = Stream.iterate(0, i -> 0).limit(capacity).collect(Collectors.toList());
    }

    public List<Integer> getList() {
        return list;
    }

    public boolean arrive(int k) {
        if (k < 0 || k > list.size() - 1) {
            throw new IllegalArgumentException();
        }

        if (list.stream().noneMatch(x -> x == 0)) {
            return false;
        }

        int index = k;
        while (list.get(index) != 0) {
            index++;
            if (index > list.size() - 1) {
                index = 0;
            }
        }
        list.set(index, 1);

        return true;
    }


    public boolean depart(int k) {
        if (k < 0 || k > list.size()) {
            throw new IllegalArgumentException();
        }

        if (list.get(k) != 0) {
            list.set(k, 0);

            return true;
        }

        return false;
    }

    public void print() {
        list.forEach(System.out::print);
    }
}
