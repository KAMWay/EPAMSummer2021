package practice4;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 {

    private static final Logger LOG = Logger.getLogger(Part4.class.getName());
    private static final Pattern patternSentences = Pattern.compile("[A-ZА-Я].*?[.!?](?=\\s|$)");
    private final String input;

    public Part4(String fileName) {
        this.input = getInput(fileName);
    }

    public static String getInput(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName), "Cp1251")) {
            do {
                int symbol = reader.read();
                sb.append((char) symbol);
            } while (reader.ready());
        } catch (IOException ex) {
            LOG.log(Level.WARNING, ex.getMessage());
        }
        return sb.toString().replaceAll(System.lineSeparator(), " ");
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        private final Matcher matcher = patternSentences.matcher(input);
        private Object nextElement = null;

        public IteratorImpl() {
            if (matcher.find()) {
                nextElement = matcher.group();
            }
        }

        @Override
        public boolean hasNext() {
            return nextElement != null;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Object element = nextElement;
            if (matcher.find())
                nextElement = matcher.group();
            else
                nextElement = null;

            return element;
        }

    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        if (input.length() == 0) return "";

        StringBuilder out = new StringBuilder();
        Iterator<Object> iterator = iterator();
        while (iterator.hasNext()) {
            out.append(iterator.next()).append(System.lineSeparator());
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Part4 object = new Part4("part4.txt");
        System.out.print(object);
    }

}
