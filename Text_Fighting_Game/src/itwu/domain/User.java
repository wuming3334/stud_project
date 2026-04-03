package itwu.domain;

import java.util.Random;

public class User {
    private String id;
    private String username;
    private String password;
    private boolean status;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User() {
        id = createId();
        status = true;
    }

    public User(String username, String password, String phone) {

        id = createId();
        this.username = username;
        this.password = password;
        this.phone = phone;
        status = true;
    }

    public String getId() {
        return id;
    }

    public String createId() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder("UID");
        for (int i = 0; i < 5; i++) {
            int num = random.nextInt(10);
            sb.append(num);
        }
        return sb.toString();
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isState() {
        return status;
    }

    public void setStatus(boolean state) {
        this.status = state;
    }
}
