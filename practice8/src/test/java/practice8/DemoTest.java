package practice8;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class DemoTest {

    private static final String DB_URL = "jdbc:h2:~/testdemo";
    private static final String URL_CONNECTION = "jdbc:h2:~/testdemo;user=youruser;password=yourpassword;";
    private static final String USER = "youruser";
    private static final String PASS = "yourpassword";

    private static final InputStream STD_IN = System.in;
    private static final PrintStream STD_OUT = System.out;

    @BeforeClass
    public static void beforeTest() throws SQLException {
        try (OutputStream output = new FileOutputStream("app.properties")) {
            Properties prop = new Properties();
            prop.setProperty("connection.url", URL_CONNECTION);
            prop.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        }

        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = con.createStatement()) {
            String sql = "DROP TABLE IF EXISTS users;\n" +
                    "CREATE TABLE users (\n" +
                    "   id INTEGER(11) NOT NULL AUTO_INCREMENT,\n" +
                    "   login VARCHAR(10) UNIQUE,\n" +
                    "   PRIMARY KEY (id));\n" +
                    "DROP TABLE IF EXISTS teams;\n" +
                    "CREATE TABLE teams (\n" +
                    "   id INTEGER(11) NOT NULL AUTO_INCREMENT,\n" +
                    "   name VARCHAR(10) UNIQUE,\n" +
                    "   PRIMARY KEY (id));\n" +
                    "DROP TABLE IF EXISTS users_teams;\n" +
                    "CREATE TABLE users_teams (\n" +
                    "   user_id INTEGER(11) NOT NULL,\n" +
                    "   team_id INTEGER(11) NOT NULL,\n" +
                    "   UNIQUE (user_id, team_id),\n" +
                    "   FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE, \n" +
                    "   FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE CASCADE" +
                    ");";
            statement.executeUpdate(sql);
        }
    }

    @Test
    public void demoTest() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        Demo.main(null);

        String sb = "[ivanov, petrov, obama]" + System.lineSeparator() +
                "[teamA, teamB, teamC]" + System.lineSeparator() +
                "[teamA]" + System.lineSeparator() +
                "[teamA, teamB]" + System.lineSeparator() +
                "[teamA, teamB, teamC]" + System.lineSeparator() +
                "[teamB, teamX]" + System.lineSeparator();
        assertEquals(sb, bos.toString());

        System.setIn(STD_IN);
        System.setOut(STD_OUT);
    }

}
