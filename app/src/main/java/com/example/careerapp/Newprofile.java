package com.example.careerapp;

public class Newprofile {


    String studid,user_name,password,gender,phoneno,fullname;

    public Newprofile(String fullname, String studid, String user_name, String password, String gender, String phoneno) {
        this.studid = studid;
        this.fullname = fullname;
        this.user_name = user_name;
        this.password = password;
        this.gender = gender;
        this.phoneno = phoneno;
    }

    public String getFullname() {return fullname;}

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public String getStudid() {
        return studid;
    }

    public void setStudid(String studid) {
        this.studid = studid;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
}
