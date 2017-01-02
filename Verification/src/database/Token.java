package database;

import java.util.Random;

public class Token {

    private int id;
    private boolean used;
    private String token;

    public Token(int id, boolean used) {
        this.setId(id);
        this.setUsed(used);
        generateToken();
    }

    private void generateToken() {
        Random rnd = new Random();
        int tokenValue = 100000 + rnd.nextInt(900000);
        setToken(String.valueOf(tokenValue));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

}
