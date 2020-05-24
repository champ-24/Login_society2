package com.example.login_society;

public class complaint_set {

    private String event_name;
    private String description;
    private String date;
    private String time;
    private String host;


    public complaint_set() {
    }

    public complaint_set(String event_name, String description, String date, String time, String host) {
        this.event_name = event_name;
        this.description = description;
        this.date = date;
        this.time = time;
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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
