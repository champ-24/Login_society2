package com.example.login_society;

class Associate_Helper {
    String ass_name,ass_contact,ass_address,ass_gender,ass_work,ass_salary;

    public Associate_Helper() {
    }

    public Associate_Helper(String ass_name, String ass_contact, String ass_address, String ass_gender, String ass_work, String ass_salary) {
        this.ass_name = ass_name;
        this.ass_contact = ass_contact;
        this.ass_address = ass_address;
        this.ass_gender = ass_gender;
        this.ass_work = ass_work;
        this.ass_salary = ass_salary;
    }

    public String getAss_name() {
        return ass_name;
    }

    public String getAss_contact() {
        return ass_contact;
    }

    public String getAss_address() {
        return ass_address;
    }

    public String getAss_gender() {
        return ass_gender;
    }

    public String getAss_work() {
        return ass_work;
    }

    public String getAss_salary() {
        return ass_salary;
    }
}
