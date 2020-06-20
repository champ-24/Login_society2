package com.example.login_society;

class associate_list_helper {
    String ass_name,ass_work;

    public associate_list_helper() {
    }

    public associate_list_helper(String ass_name, String ass_work) {
        this.ass_name = ass_name;
        this.ass_work = ass_work;
    }

    public String getAss_name() {
        return ass_name;
    }

    public void setAss_name(String ass_name) {
        this.ass_name = ass_name;
    }

    public String getAss_work() {
        return ass_work;
    }

    public void setAss_work(String ass_work) {
        this.ass_work = ass_work;
    }
}
