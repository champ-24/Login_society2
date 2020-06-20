package com.example.login_society;

public class notice {

    private String description;
    private String host;


    public notice() {
    }

    public notice(String description, String host) {
        this.description = description;
        this.host = host;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}

