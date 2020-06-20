package com.example.login_society;

public class Event_helper {

    String event_name,date,host;

    public Event_helper() {
    }

    public Event_helper(String event_name, String date, String host) {
        this.event_name = event_name;
        this.date = date;
        this.host = host;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
