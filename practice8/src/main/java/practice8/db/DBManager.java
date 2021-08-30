package practice8.db;

import practice8.db.entity.Team;
import practice8.db.entity.User;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBManager {

    private static final Logger LOG = Logger.getLogger(DBManager.class.getName());

    private static DBManager dbManager;
    private static Connection connection;
    private static final Lock CONNECTION_LOCK = new ReentrantLock();

    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String SELECT_ALL_TEAMS = "SELECT * FROM teams";
    private static final String INSERT_USER = "INSERT INTO users VALUES (DEFAULT ,?)";
    private static final String INSERT_TEAM = "INSERT INTO teams VALUES (DEFAULT ,?)";
    private static final String SELECT_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
    private static final String SELECT_TEAM_BY_LOGIN = "SELECT * FROM teams WHERE name=?";
    private static final String SELECT_TEAMS_BY_USER_ID = "" +
            "SELECT t.id, t.name FROM users_teams ut\n" +
            "JOIN users u ON ut.user_id = u.id\n" +
            "JOIN teams t ON ut.team_id = t.id\n" +
            "WHERE u.id = ?";

    private static final String INSERT_USER_TO_TEAM = "INSERT INTO users_teams VALUES (?, ?)";
    private static final String DELETE_TEAM = "DELETE FROM teams WHERE name=?";
    private static final String UPDATE_TEAM = "UPDATE teams SET name=? WHERE id=?";

    public static void getConnection() {
        try (FileInputStream fis = new FileInputStream("app.properties")) {
            Properties property = new Properties();
            property.load(fis);
            String url = property.getProperty("connection.url");
            connection = DriverManager.getConnection(url);
        } catch (Exception e) {
            LOG.log(Level.WARNING, e.getMessage());
        }
    }

    private DBManager() {
        getConnection();
    }

    public static synchronized DBManager getInstance() {
        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
    }

    public boolean insertUser(User user) {
        ResultSet id = null;
        boolean locked = false;
        try (PreparedStatement ps = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            CONNECTION_LOCK.lock();
            locked = true;
            ps.setString(1, user.getLogin());
            if (ps.executeUpdate() != 1) {
                return false;
            }
            id = ps.getGeneratedKeys();
            if (id.next()) {
                int idField = id.getInt(1);
                user.setId(idField);
            }
        } catch (Exception e) {
            return false;
        } finally {
            close(id);
            if (locked) CONNECTION_LOCK.unlock();
        }
        return true;
    }

    public boolean insertTeam(Team team) {
        ResultSet id = null;
        boolean locked = false;
        try (PreparedStatement ps = connection.prepareStatement(INSERT_TEAM, Statement.RETURN_GENERATED_KEYS)) {
            CONNECTION_LOCK.lock();
            locked = true;
            ps.setString(1, team.getName());
            if (ps.executeUpdate() != 1) {
                return false;
            }
            id = ps.getGeneratedKeys();
            if (id.next()) {
                int idField = id.getInt(1);
                team.setId(idField);
            }
        } catch (Exception e) {
            return false;
        } finally {
            close(id);
            if (locked) CONNECTION_LOCK.unlock();
        }
        return true;
    }

    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        boolean locked = false;
        try (Statement ps = connection.createStatement();
             ResultSet rs = ps.executeQuery(SELECT_ALL_USERS)
        ) {
            CONNECTION_LOCK.lock();
            locked = true;
            while (rs.next()) {
                User user = new User();
                users.add(user);
                user.setId(rs.getInt(1));
                user.setLogin(rs.getString(2));
            }
        } catch (Exception e) {
            return Collections.emptyList();
        } finally {
            if (locked) CONNECTION_LOCK.unlock();
        }
        return users;
    }

    public List<Team> findAllTeams() {
        List<Team> teams = new ArrayList<>();
        boolean locked = false;
        try (Statement ps = connection.createStatement();
             ResultSet rs = ps.executeQuery(SELECT_ALL_TEAMS)
        ) {
            CONNECTION_LOCK.lock();
            locked = true;
            while (rs.next()) {
                Team team = new Team();
                teams.add(team);
                team.setId(rs.getInt(1));
                team.setName(rs.getString(2));
            }
        } catch (Exception e) {
            return Collections.emptyList();
        } finally {
            if (locked) CONNECTION_LOCK.unlock();
        }
        return teams;
    }

    public User getUser(String login) {
        ResultSet rs = null;
        User user = null;
        boolean locked = false;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_LOGIN)) {
            CONNECTION_LOCK.lock();
            locked = true;
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
            }
        } catch (SQLException e) {
            LOG.log(Level.WARNING, e.getMessage());
        } finally {
            close(rs);
            if (locked) CONNECTION_LOCK.unlock();
        }
        return user;
    }

    public Team getTeam(String name) {
        ResultSet rs = null;
        Team team = null;
        boolean locked = false;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_TEAM_BY_LOGIN)) {
            CONNECTION_LOCK.lock();
            locked = true;
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                team = new Team();
                team.setId(rs.getInt("id"));
                team.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            LOG.log(Level.WARNING, e.getMessage());
        } finally {
            close(rs);
            if (locked) CONNECTION_LOCK.unlock();
        }
        return team;
    }

    public List<Team> getUserTeams(User user) {
        ResultSet rs = null;
        List<Team> teams = new ArrayList<>();
        boolean locked = false;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_TEAMS_BY_USER_ID)) {
            CONNECTION_LOCK.lock();
            locked = true;
            ps.setInt(1, user.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                Team team = new Team();
                teams.add(team);
                team.setId(rs.getInt(1));
                team.setName(rs.getString(2));
            }
        } catch (Exception e) {
            return Collections.emptyList();
        } finally {
            close(rs);
            if (locked) CONNECTION_LOCK.unlock();
        }
        return teams;
    }

    public boolean setTeamsForUser(User user, Team... teams) {
        boolean locked = false;
        try (PreparedStatement ps = connection.prepareStatement(INSERT_USER_TO_TEAM)) {
            CONNECTION_LOCK.lock();
            locked = true;
            connection.setAutoCommit(false);
            for (Team t : teams) {
                ps.setInt(1, user.getId());
                ps.setInt(2, t.getId());
                ps.addBatch();
            }
            int[] usersTeams = ps.executeBatch();
            for (int i : usersTeams) {
                if (i != 1) {
                    return false;
                }
            }
            connection.commit();
            return true;
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOG.log(Level.WARNING, e.getMessage());
            }
            LOG.log(Level.WARNING, ex.getMessage());
        } finally {
            setAutocommit();
            if (locked) CONNECTION_LOCK.unlock();
        }
        return false;
    }

    public boolean deleteTeam(Team team) {
        boolean locked = false;
        try (PreparedStatement ps = connection.prepareStatement(DELETE_TEAM)) {
            CONNECTION_LOCK.lock();
            locked = true;
            ps.setString(1, team.getName());
            if (ps.executeUpdate() != 1) {
                return false;
            }
        } catch (Exception e) {
            return false;
        } finally {
            if (locked) CONNECTION_LOCK.unlock();
        }
        return true;
    }

    public boolean updateTeam(Team team) {
        boolean locked = false;
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_TEAM)) {
            CONNECTION_LOCK.lock();
            locked = true;
            ps.setString(1, team.getName());
            ps.setInt(2, team.getId());
            if (ps.executeUpdate() != 1) {
                return false;
            }
        } catch (Exception e) {
            return false;
        } finally {
            if (locked) CONNECTION_LOCK.unlock();
        }
        return true;
    }

    private static void setAutocommit() {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            LOG.log(Level.WARNING, e.getMessage());
        }
    }

    private static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                LOG.log(Level.WARNING, e.getMessage());
            }
        }
    }

}
