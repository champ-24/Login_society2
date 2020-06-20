package com.example.login_society;

public class User {

    private String event_name;
    private String description;
    private String date;
    private String time;
    private String host;


    public User() {
    }

    public User(String host,String event_name, String description, String date, String time) {
        this.host = host;
        this.event_name = event_name;
        this.description = description;
        this.date = date;
        this.time = time;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
