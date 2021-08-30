package practice6.part6;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;

public class Part62 {

    public static void main(String[] args) {
        final String fileName;
        if (args.length == 1 && args[0].contains(".")) {
            fileName = args[0];
        } else {
            fileName = "part6.txt";
        }
        List<String> input = Part6.getInput(fileName);

        Map<String, Integer> wordsLengthMap = getWordsWithLength(input);
        Map<String, Integer> sortMap = getFirst3SortString(wordsLengthMap);

        print(sortMap);
    }

    public static Map<String, Integer> getWordsWithLength(List<String> list) {
        return list.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        String::length,
                        (a1, a2) -> a1,
                        LinkedHashMap::new
                ));
    }

    public static Map<String, Integer> getFirst3SortString(Map<String, Integer> map) {
        return map.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(3)
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e2,
                        LinkedHashMap::new
                ));
    }

    public static void print(Map<String, Integer> map) {
        map.forEach((key, value) -> System.out.println(key + " ==> " + value));
    }

}
