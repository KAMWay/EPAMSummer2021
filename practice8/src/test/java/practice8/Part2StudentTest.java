package practice8;

import practice8.db.DBManager;
import practice8.db.entity.Team;
import org.junit.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static org.junit.Assert.*;

public class Part2StudentTest {

    private static DBManager dbManager;

    private static final String DB_URL = "jdbc:h2:~/test2";
    private static final String URL_CONNECTION = "jdbc:h2:~/test2;user=youruser;password=yourpassword;";
    private static final String USER = "youruser";
    private static final String PASS = "yourpassword";

    @BeforeClass
    public static void beforeTest() throws SQLException {
        try (OutputStream output = new FileOutputStream("app.properties")) {
            Properties prop = new Properties();
            prop.setProperty("connection.url", URL_CONNECTION);
            prop.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        }

        dbManager = DBManager.getInstance();

        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = con.createStatement()) {
            String sql = "DROP TABLE IF EXISTS teams;\n" +
                    "CREATE TABLE teams (\n" +
                    "   id INTEGER(11) NOT NULL AUTO_INCREMENT,\n" +
                    "   name VARCHAR(10) UNIQUE,\n" +
                    "   PRIMARY KEY (id)" +
                    ");";
            statement.executeUpdate(sql);
        }
        dbManager.insertTeam(Team.createTeam("teamA"));
    }

    @Test
    public void test2() {
        assertNotNull(dbManager.findAllTeams());
    }

    @AfterClass
    public static void printBase() {
        System.out.println("+2+" );
        for (Team team:dbManager.findAllTeams()){
            System.out.println(team.getName()+":id" + team.getId());
        }
    }

}