package practice6.part1;

public class Word implements Comparable<Word> {

    private final String content;

    private int frequency;

    public Word(String content, int frequency) {
        this.content = content;
        this.frequency = frequency;
    }

    public String getContent() {
        return content;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Word word = (Word) obj;

        return frequency == word.frequency && content.equals(word.content);
    }

    @Override
    public int hashCode() {
        int result = (content == null) ? 0 : content.hashCode();

        result = 31 * result + frequency;

        return result;
    }

    @Override
    public int compareTo(Word o) {
        int cmp = Integer.compare(o.frequency, this.frequency);

        if (cmp != 0) {
            return cmp;
        }

        return this.content.compareTo(o.content);
    }

    @Override
    public String toString() {
        return content + " : " + frequency;
    }
}
