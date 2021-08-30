package practice8;

import practice8.db.entity.User;
import practice8.db.DBManager;
import practice8.db.entity.Team;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static org.junit.Assert.*;

public class Part3StudentTest {

    private static DBManager dbManager;

    private static final String DB_URL = "jdbc:h2:~/test3";
    private static final String URL_CONNECTION = "jdbc:h2:~/test3;user=youruser;password=yourpassword";
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
            String sql = "DROP TABLE IF EXISTS users_teams;\n" +
                    "DROP TABLE IF EXISTS users;\n" +
                    "DROP TABLE IF EXISTS teams;\n" +
                    "CREATE TABLE users (\n" +
                    "   id INTEGER(11) NOT NULL AUTO_INCREMENT,\n" +
                    "   login VARCHAR(10) UNIQUE,\n" +
                    "   PRIMARY KEY (id)" +
                    ");\n" +
                    "CREATE TABLE teams (\n" +
                    "   id INTEGER(11) NOT NULL AUTO_INCREMENT,\n" +
                    "   name VARCHAR(10) UNIQUE,\n" +
                    "   PRIMARY KEY (id)" +
                    ");\n" +
                    "CREATE TABLE users_teams (\n" +
                    "   user_id INTEGER(11) NOT NULL,\n" +
                    "   team_id INTEGER(11) NOT NULL,\n" +
                    "   UNIQUE (user_id, team_id),\n" +
                    "   FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,\n" +
                    "   FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE CASCADE" +
                    ");";

            statement.executeUpdate(sql);
        }

        dbManager.insertUser(User.createUser("petrov"));
        dbManager.insertUser(User.createUser("ivanov"));
        dbManager.insertUser(User.createUser("obama"));

        dbManager.insertTeam(Team.createTeam("teamA"));
        dbManager.insertTeam(Team.createTeam("teamB"));
        dbManager.insertTeam(Team.createTeam("teamC"));
    }

    @Test
    public void test3() {
        Team teamA = dbManager.getTeam("teamA");
        Team teamB = dbManager.getTeam("teamB");
        Team teamC = dbManager.getTeam("teamC");

        User userIvanov = dbManager.getUser("ivanov");
        User userPetrov = dbManager.getUser("petrov");
        User userObama = dbManager.getUser("obama");

        dbManager.setTeamsForUser(userIvanov, teamA);
        dbManager.setTeamsForUser(userPetrov, teamA, teamB);
        dbManager.setTeamsForUser(userObama, teamA, teamB, teamC);

        assertEquals(1, dbManager.getUserTeams(userIvanov).size());
        assertEquals(2, dbManager.getUserTeams(userPetrov).size());
        assertEquals(3, dbManager.getUserTeams(userObama).size());
    }

    @Test
    public void test4Null() {
        assertEquals("[]", dbManager.getUserTeams(null).toString());
    }

    @AfterClass
    public static void printBase() {
        System.out.println("+3+" + dbManager.findAllUsers());
        System.out.println("+3+" + dbManager.findAllTeams());
        for (User user : dbManager.findAllUsers()) {
            System.out.println(user + ":" + dbManager.getUserTeams(user));
        }
    }

}