package com.example.careerapp;

public class bookingprof {

    String fullname,phoneno,studentid,gender,date_timeofapppoint;

    public bookingprof() {}

    public bookingprof(String fullname, String phoneno, String studentid, String gender, String date_timeofapppoint) {
        this.fullname = fullname;
        this.phoneno = phoneno;
        this.studentid = studentid;
        this.gender = gender;
        this.date_timeofapppoint = date_timeofapppoint;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDate_timeofapppoint() {
        return date_timeofapppoint;
    }

    public void setDate_timeofapppoint(String date_timeofapppoint) {
        this.date_timeofapppoint = date_timeofapppoint;
    }
}
