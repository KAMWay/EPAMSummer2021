package practice6.part6;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Part63 {

    public static void main(String[] args) {
        final String fileName;
        if (args.length == 1 && args[0].contains(".")) {
            fileName = args[0];
        } else {
            fileName = "part6.txt";
        }
        List<String> input = Part6.getInput(fileName);

        List<String> duplicatesList = getDuplicatesStringsList(input);
        duplicatesList = getFirst3DuplicateToList(input, duplicatesList);
        duplicatesList = reversStringAndUpper(duplicatesList);

        duplicatesList.forEach(System.out::println);
    }

    public static List<String> getDuplicatesStringsList(List<String> list) {
        return list.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(x -> x.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static List<String> getFirst3DuplicateToList(List<String> words, List<String> duplicateWords) {
        List<String> out = new ArrayList<>();
        for (String word : words) {
            if (duplicateWords.contains(word)) {
                out.add(word);
                if (out.size() == 3) {
                    return out;
                }
            }
        }

        return out;
    }

    public static List<String> reversStringAndUpper(List<String> words) {
        List<String> out = new ArrayList<>();
        for (String word : words) {
            StringBuilder wordRevers = new StringBuilder();
            wordRevers.append(word.toUpperCase());
            wordRevers.reverse();
            out.add(wordRevers.toString());
        }

        return out;
    }

}
