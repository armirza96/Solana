package com.winners.solana;

import com.google.firebase.database.Exclude;

public class User {
    private String Username;

    public User(String username) {
        Username = username;
    }

    public User() {
    }


    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
