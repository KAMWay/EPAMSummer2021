package practice5;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part2 {

    private static final Logger LOG = Logger.getLogger(Part2.class.getName());

    private static final InputStream STD_IN = System.in;

    public static void main(final String[] args) {
        ByteArrayInputStream in = new ByteArrayInputStream(
                "^".replace("^", System.lineSeparator()).getBytes());
        System.setIn(in);

        Thread thread = new Thread(() -> Spam.main(null));
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOG.log(Level.WARNING, e.getMessage());
        }

        try {
            thread.join();
        } catch (InterruptedException e) {
            thread.interrupt();
            LOG.log(Level.WARNING, e.getMessage());
        }

        System.setIn(STD_IN);
    }

}