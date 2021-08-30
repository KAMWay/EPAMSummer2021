package practice8;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import practice8.db.DBManager;
import practice8.db.entity.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static org.junit.Assert.*;

public class Part1StudentTest {

    private static DBManager dbManager;

    private static final String DB_URL = "jdbc:h2:~/test1";
    private static final String URL_CONNECTION = "jdbc:h2:~/test1;user=youruser;password=yourpassword;";
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
            String sql = "DROP TABLE IF EXISTS users;\n" +
                    "CREATE TABLE users (\n" +
                    "   id INTEGER(11) NOT NULL AUTO_INCREMENT,\n" +
                    "   login VARCHAR(10) UNIQUE,\n" +
                    "   PRIMARY KEY (id)" +
                    ");";
            statement.executeUpdate(sql);
        }
        dbManager.insertUser(User.createUser("obama"));
    }

    @Test
    public void test1() {
        dbManager.insertUser(User.createUser("obama"));
        assertNotNull(dbManager.findAllUsers());
    }

    @Test
    public void test4Null() {
        assertNull(dbManager.getUser(null));
    }

    @Test
    public void test4Equals() {
        dbManager.insertUser(User.createUser("obama"));
        User userPetrov = new User();
        userPetrov.setLogin("obama");
        userPetrov.setId(dbManager.getUser("obama").getId());
        assertEquals(dbManager.getUser("obama"), userPetrov);
    }

    @AfterClass
    public static void printBase() {
        System.out.println("+1+" );
        for (User user:dbManager.findAllUsers()){
            System.out.println(user.getLogin()+":id" + user.getId());
        }
    }

}