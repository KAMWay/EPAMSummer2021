package practice6.part1;

import java.util.*;
import java.util.stream.Collectors;

public class WordContainer {

    private List<Word> words;

    public WordContainer() {
        this.words = new ArrayList<>();
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWord(String curWord) {
        boolean flag = true;
        for (Word word : words) {
            if (word.getContent().equals(curWord)) {
                word.setFrequency(word.getFrequency() + 1);
                flag = false;
                break;
            }
        }

        if (flag) {
            words.add(new Word(curWord, 1));
        }
    }

    @Override
    public String toString() {
        return words.stream()
                .map(Word::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public void sort(){
        words = words.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        WordContainer wordContainer = new WordContainer();

        Scanner scanner = new Scanner(System.in);
        String str = "";
        do {
            if (!scanner.hasNext()) {
                continue;
            }

            str = scanner.next();
            if (!str.equals("stop")) {
                wordContainer.setWord(str);
            }
        } while (!str.equals("stop") && scanner.hasNext());

        wordContainer.sort();
        System.out.println(wordContainer);
    }

}
