package com.zy.dndserver.entities;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

@Entity("users")
public class User {
    @Id
    private Long Uid;
    private String username;
    private String password;

    public User(Long uid, String username, String password) {
        Uid = uid;
        this.username = username;
        this.password = password;
    }

    public Long getUid() {
        return Uid;
    }

    public void setUid(Long uid) {
        Uid = uid;
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
}
