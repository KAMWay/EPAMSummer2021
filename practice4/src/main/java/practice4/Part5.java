package practice4;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part5 {

    private static final Logger LOG = Logger.getLogger(Part5.class.getName());

    private static String getOutput(String locate) {
        try {
            String[] param = locate.split(" ");
            Locale locale = new Locale(param[1]);
            ResourceBundle rb = ResourceBundle.getBundle("resources", locale);
            return rb.getString(param[0]);
        } catch (MissingResourceException ex) {
            LOG.log(Level.WARNING, ex.getMessage());
            return "";
        }
    }

    private static void getLocalization(Scanner scanner) {
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(scanner.next());
            if (sb.toString().split(" ").length > 1 && !sb.toString().contains("stop")) {
                System.out.println(getOutput(sb.toString()));
                sb = new StringBuilder();
            } else sb.append(" ");
        } while (!sb.toString().contains("stop"));
    }

    public static void main(String[] args) {
        getLocalization(new Scanner(System.in));
    }

}
