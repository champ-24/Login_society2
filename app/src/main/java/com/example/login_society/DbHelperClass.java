package com.example.login_society;

public class DbHelperClass {

    String full_name;
    String society_name;
    String society_addr;
    String phoneno;
    String officeno;
    String email;

    public DbHelperClass( ) {
    }

    public DbHelperClass(String full_name, String society_name, String society_addr, String phoneno, String officeno, String email) {
        this.full_name = full_name;
        this.society_name = society_name;
        this.society_addr = society_addr;
        this.phoneno = phoneno;
        this.officeno = officeno;
        this.email = email;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getSociety_name() {
        return society_name;
    }

    public void setSociety_name(String society_name) {
        this.society_name = society_name;
    }

    public String getSociety_addr() {
        return society_addr;
    }

    public void setSociety_addr(String society_addr) {
        this.society_addr = society_addr;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getOfficeno() {
        return officeno;
    }

    public void setOfficeno(String officeno) {
        this.officeno = officeno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
