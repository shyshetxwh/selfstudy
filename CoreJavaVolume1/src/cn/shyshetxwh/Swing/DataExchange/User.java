package cn.shyshetxwh.Swing.DataExchange;

import java.util.Arrays;

public class User {
    private String username;
    private char[] password;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password=" + Arrays.toString(password) +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public User() {

    }

    public User(String username, char[] password) {

        this.username = username;
        this.password = password;
    }
}
