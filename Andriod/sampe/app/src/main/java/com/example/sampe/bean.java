package com.example.sampe;

import java.io.Serializable;

class bean implements Serializable {
    String Name,Age,Mobile,Addr;

    public bean() {
    }

    public bean(String name, String age, String mobile, String addr) {
        Name = name;
        Age = age;
        Mobile = mobile;
        Addr = addr;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getAddr() {
        return Addr;
    }

    public void setAddr(String addr) {
        Addr = addr;
    }
}
