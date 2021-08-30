package practice6.part6;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Part61 {

    public static void main(String[] args) {
        final String fileName;
        if (args.length == 1 && args[0].contains(".")) {
            fileName = args[0];
        } else {
            fileName = "part6.txt";
        }
        List<String> input = Part6.getInput(fileName);

        Map<String, Long> wordsFrequencyMap = getWordsWithFrequency(input);
        Long frequency = getMostOften3Frequency(wordsFrequencyMap);

        print(getFirst3StringWithFrequency(input, wordsFrequencyMap, frequency));
    }

    public static Map<String, Long> getWordsWithFrequency(List<String> list) {
        return list.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));
    }

    public static Long getMostOften3Frequency(Map<String, Long> map) {
        return Objects.requireNonNull(map
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(3)
                .min(Map.Entry.comparingByValue())
                .orElse(null)).getValue();
    }

    public static Map<String, Long> getFirst3StringWithFrequency(List<String> words, Map<String, Long> map, Long frequency) {
        Long maxFrequency = Collections.max(map.values());

        Map<String, Long> out = new HashMap<>();
        for (Long curF = maxFrequency; curF >= frequency; curF--) {
            for (String word : words) {
                if (map.get(word).equals(curF)) {
                    out.put(word, map.get(word));
                    if (out.size() == 3) {

                        return getReversSortMapByKey(out);
                    }
                }
            }
        }

        return getReversSortMapByKey(out);
    }

    private static Map<String, Long> getReversSortMapByKey(Map<String, Long> map) {
        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    public static void print(Map<String, Long> map) {
        map.forEach((key, value) -> System.out.println(key + " ==> " + value));
    }

}
