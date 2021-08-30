package practice8.db.entity;

public class User {

    private Integer id;
    private String login;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public static User createUser(String login) {
        User user = new User();
        user.setLogin(login);
        user.setId(-1);
        return user;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User other = (User) obj;
        if (login == null) {
            return other.login == null;
        } else return login.equals(other.login);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ((login == null) ? 0 : login.hashCode());
        result = 31 * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return login;
    }

}
