package com.example.chat_system.attemp2;

class database_helper_signup {
    String email,pwd;

    public database_helper_signup() {
    }

    public database_helper_signup(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
