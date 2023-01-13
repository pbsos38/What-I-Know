package com.example.firebase_database;

class signup_dataBaseHelper {

String email,phone,pwd;
    public signup_dataBaseHelper(){

    }

    public signup_dataBaseHelper(String email, String phone, String pwd) {
        this.email = email;
        this.phone = phone;
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
